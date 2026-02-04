package com.example.splitbill.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.splitbill.domain.model.Person
import com.example.splitbill.ui.viewmodel.AddExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: AddExpenseViewModel = hiltViewModel(),
    expenseId: Long = -1L
) {
    val people by viewModel.people.collectAsState()
    val expenseState by viewModel.expenseState.collectAsState()
    
    var description by remember { mutableStateOf("") }
    var amountStr by remember { mutableStateOf("") }
    var expandedPayer by remember { mutableStateOf(false) }
    var selectedPayer by remember { mutableStateOf<Person?>(null) }
    
    // Split Mode
    var isUnequalSplit by remember { mutableStateOf(false) }

    // Multi-select for equal split
    var selectedSplitIds by remember { mutableStateOf(setOf<Long>()) }
    
    // Amounts for unequal split
    var unequalAmounts by remember { mutableStateOf(mapOf<Long, String>()) }

    // Logic to initialize unequal amounts when switching mode
    // or when amount changes (could distribute equally initially)
    
    // Load existing expense
    LaunchedEffect(expenseId) {
        if (expenseId != -1L) {
            viewModel.loadExpense(expenseId)
        }
    }

    // Populate UI from loaded expense
    LaunchedEffect(expenseState) {
        expenseState?.let { expense ->
            description = expense.description
            amountStr = expense.amount.toString()
            // Wait for people to assume they are loaded, or just find by ID
            // Ideally we need people list to find the payer object
        }
    }

    // Populate Payer and Shares once people are loaded and expense is loaded
    LaunchedEffect(expenseState, people) {
        val expense = expenseState
        if (expense != null && people.isNotEmpty() && selectedPayer == null) {
            selectedPayer = people.find { it.id == expense.payerId }
            
            if (expense.shares.isNotEmpty()) {
                // Determine if it was unequal or equal
                // For simplified logic: if shares exist, assume we might need unequal mode
                // But if all shares are equal, we could default to equal mode.
                // Let's just default to Unequal if shares are present to be precise.
                // Or check values.
                val distinctAmounts = expense.shares.values.distinct()
                val isRoughlyEqual = distinctAmounts.size == 1
                
                if (isRoughlyEqual && expense.shares.keys.containsAll(people.map { it.id })) {
                     // Maybe it was "All" equal
                     isUnequalSplit = false
                     selectedSplitIds = expense.shares.keys
                } else {
                     isUnequalSplit = true
                     unequalAmounts = expense.shares.mapValues { it.value.toString() }
                     selectedSplitIds = expense.shares.keys // Sync this just in case
                }
            } else {
                // Old "Equal Split All" logic
                 isUnequalSplit = false
                 selectedSplitIds = people.map { it.id }.toSet()
            }
        } else if (expense == null && people.isNotEmpty() && selectedSplitIds.isEmpty()) {
            // New Expense Default
             selectedSplitIds = people.map { it.id }.toSet()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (expenseId == -1L) "Add Expense" else "Edit Expense") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val amount = amountStr.toDoubleOrNull() ?: 0.0
                    val currentPayer = selectedPayer // local copy for smart cast
                    
                    if (currentPayer != null && amount > 0) {
                        val finalShares = if (isUnequalSplit) {
                            unequalAmounts.mapValues { it.value.toDoubleOrNull() ?: 0.0 }
                                .filter { it.value > 0 }
                        } else {
                            if (selectedSplitIds.isNotEmpty()) {
                                val splitAmount = amount / selectedSplitIds.size
                                selectedSplitIds.associateWith { splitAmount }
                            } else {
                                emptyMap()
                            }
                        }
                        
                        // Validation for unequal: sum should effectively match amount
                        val totalAllocated = finalShares.values.sum()
                        if (isUnequalSplit && Math.abs(totalAllocated - amount) > 0.02) {
                             // Show error? For now just proceeding, business logic might handle or data will be inconsistent (debt < expense)
                             // Ideally we show a snackbar.
                        }

                        viewModel.saveExpense(
                            id = expenseId,
                            description = description,
                            amount = amount,
                            payerId = currentPayer.id,
                            shares = finalShares
                        )
                        navController.popBackStack()
                    }
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = "Save")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = amountStr,
                onValueChange = { amountStr = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

             // Payer Selection (Autocomplete)
            var payerNameInput by remember { mutableStateOf("") }
            
            // Sync text when selectedPayer changes externally (e.g. initial load)
            LaunchedEffect(selectedPayer) {
                if (selectedPayer != null && selectedPayer!!.name != payerNameInput) {
                    payerNameInput = selectedPayer!!.name
                }
            }

            val filteredPeople = people.filter { 
                it.name.contains(payerNameInput, ignoreCase = true) 
            }
            
             // Auto-select logic on type
            LaunchedEffect(payerNameInput) {
                 val match = people.find { it.name.equals(payerNameInput.trim(), ignoreCase = true) }
                 if (match != null) selectedPayer = match
            }

            Column {
                OutlinedTextField(
                    value = payerNameInput,
                    onValueChange = { 
                        payerNameInput = it
                        if (people.none { p -> p.name.equals(it, ignoreCase = true) }) {
                             selectedPayer = null
                        }
                    },
                    label = { Text("Payer Name") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        if (selectedPayer != null) {
                            Icon(Icons.Default.Check, contentDescription = "Valid Payer", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                )
                
                 if (payerNameInput.isNotBlank() && selectedPayer == null) {
                    Card(
                        modifier = Modifier.fillMaxWidth().heightIn(max = 150.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        LazyColumn {
                            items(filteredPeople) { person ->
                                ListItem(
                                    headlineContent = { Text(person.name) },
                                    modifier = Modifier.clickable {
                                        selectedPayer = person
                                        payerNameInput = person.name
                                    }
                                )
                            }
                        }
                    }
                 }
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                 Text("Split Type:", style = MaterialTheme.typography.titleMedium)
                 Row(verticalAlignment = Alignment.CenterVertically) {
                     Text("Equal")
                     Switch(
                         checked = isUnequalSplit,
                         onCheckedChange = { isUnequalSplit = it }
                     )
                     Text("Unequal")
                 }
            }
            
            if (isUnequalSplit) {
                 val currentTotal = unequalAmounts.values.sumOf { it.toDoubleOrNull() ?: 0.0 }
                 val targetTotal = amountStr.toDoubleOrNull() ?: 0.0
                 val diff = targetTotal - currentTotal
                 
                 Text(
                     "Remaining to allocate: ${"%.2f".format(diff)}",
                     color = if (Math.abs(diff) < 0.01) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                 )
            }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(people) { person ->
                    if (isUnequalSplit) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                        ) {
                            Text(person.name, modifier = Modifier.weight(1f))
                            OutlinedTextField(
                                value = unequalAmounts[person.id] ?: "",
                                onValueChange = { newAmt ->
                                    unequalAmounts = unequalAmounts.toMutableMap().apply { put(person.id, newAmt) }
                                },
                                label = { Text("$") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.width(100.dp)
                            )
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val current = selectedSplitIds.toMutableSet()
                                    if (current.contains(person.id)) {
                                        current.remove(person.id)
                                    } else {
                                        current.add(person.id)
                                    }
                                    selectedSplitIds = current
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = selectedSplitIds.contains(person.id),
                                onCheckedChange = null // Handled by row click
                            )
                            Text(text = person.name, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }
    }
}
