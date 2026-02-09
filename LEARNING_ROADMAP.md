# 🗺️ Learning Roadmap: Building Split Bill Calculator App

This comprehensive guide covers everything you need to learn to build the Split Bill Calculator app from scratch in both **Kotlin/Android (Jetpack Compose)** and **Flutter**.

---

# Table of Contents

1. [Kotlin/Android Roadmap](#kotlinandroid-roadmap)
2. [Flutter Roadmap](#flutter-roadmap)
3. [Framework Comparison](#framework-comparison)
4. [Resources Summary](#resources-summary)

---

# Kotlin/Android Roadmap

## **Phase 1: Programming Fundamentals** (3-4 weeks)

### Week 1-2: Kotlin Basics
- [ ] Variables, data types, null safety (`?`, `!!`, `?.`)
- [ ] Functions, lambdas, higher-order functions
- [ ] Classes, data classes, objects
- [ ] Collections (List, Map, Set) and operations (map, filter, forEach)
- [ ] Control flow (if, when, loops)

**Resources**: 
- [Kotlin Koans](https://kotlinlang.org/docs/koans.html)
- [Official Kotlin Docs](https://kotlinlang.org/docs/getting-started.html)

### Week 3-4: Advanced Kotlin
- [ ] Extension functions
- [ ] Sealed classes & enums
- [ ] Generics
- [ ] Scope functions (let, apply, with, run, also)
- [ ] Coroutines basics (suspend functions, launch, async)

**Checkpoint**: Build a simple CLI calculator with Kotlin

---

## **Phase 2: Android Fundamentals** (2-3 weeks)

### Week 5-6: Android Basics
- [ ] Activity lifecycle
- [ ] Layouts (XML basics - even though we use Compose)
- [ ] Resources (strings, colors, dimensions)
- [ ] Intents & navigation
- [ ] Gradle build system basics

### Week 7: Modern Android Architecture
- [ ] **MVVM Pattern** (Model-View-ViewModel)
- [ ] **Repository Pattern**
- [ ] Separation of concerns (UI, Domain, Data layers)
- [ ] Why Clean Architecture matters

**Resources**: 
- [Android Developer Codelabs](https://developer.android.com/courses)

**Checkpoint**: Build a "Hello World" app with MVVM structure

---

## **Phase 3: Jetpack Compose UI** (3-4 weeks)

### Week 8-9: Compose Basics
- [ ] Composable functions (`@Composable`)
- [ ] State management (`remember`, `mutableStateOf`)
- [ ] Modifiers (padding, fillMaxWidth, clickable)
- [ ] Basic layouts (Column, Row, Box, LazyColumn)
- [ ] Material Design 3 components (Button, TextField, Card, Scaffold)

### Week 10-11: Advanced Compose
- [ ] State hoisting
- [ ] Side effects (`LaunchedEffect`, `DisposableEffect`)
- [ ] ViewModel integration (`collectAsState`)
- [ ] Custom composables
- [ ] Theming (Color, Typography, Shapes)
- [ ] Navigation Compose

**Resources**: 
- [Compose Pathway](https://developer.android.com/courses/pathways/compose)
- [Compose Samples](https://github.com/android/compose-samples)

**Checkpoint**: Build a multi-screen app with forms and lists

---

## **Phase 4: Data Persistence (Room Database)** (2 weeks)

### Week 12-13: Room Basics
- [ ] Entities (`@Entity`, `@PrimaryKey`)
- [ ] DAOs (`@Dao`, `@Insert`, `@Update`, `@Query`)
- [ ] Database class (`@Database`)
- [ ] Type converters (for Date, Map, etc.)
- [ ] Migrations and versioning
- [ ] Flow-based queries for reactive data

**Key Concept**: Understanding the Entity → DAO → Repository flow

**Resources**:
- [Room Codelab](https://developer.android.com/codelabs/android-room-with-a-view-kotlin)

**Checkpoint**: Create a "Notes App" with CRUD operations

---

## **Phase 5: Dependency Injection (Hilt)** (1-2 weeks)

### Week 14-15: Hilt/Dagger Basics
- [ ] What is Dependency Injection and why?
- [ ] `@HiltAndroidApp`, `@AndroidEntryPoint`
- [ ] `@Inject` constructor
- [ ] `@Module` and `@Provides`
- [ ] `@HiltViewModel`
- [ ] Scopes (`@Singleton`, `@ViewModelScoped`)

**Resources**: 
- [Hilt Documentation](https://developer.android.com/training/dependency-injection/hilt-android)

**Checkpoint**: Refactor your Notes App to use Hilt

---

## **Phase 6: Asynchronous Programming** (1-2 weeks)

### Week 16-17: Coroutines & Flow
- [ ] Coroutine builders (`launch`, `async`)
- [ ] Suspend functions
- [ ] ViewModelScope, LifecycleScope
- [ ] Flow basics (emit, collect)
- [ ] StateFlow vs SharedFlow
- [ ] Combining flows (`combine`)

**This project uses**: `StateFlow` for UI state, `Flow` for database queries

**Resources**:
- [Coroutines Codelab](https://developer.android.com/codelabs/kotlin-coroutines)

**Checkpoint**: Add background tasks to your Notes App (e.g., auto-save)

---

## **Phase 7: Project Architecture & Logic** (2-3 weeks)

### Week 18-19: Design Patterns
- [ ] Use Cases (Single Responsibility)
- [ ] Repository pattern (abstract data sources)
- [ ] Domain models vs Entities
- [ ] Mappers (Entity ↔ Domain conversion)

### Week 20: Algorithm Design
- [ ] Debt simplification algorithm logic
- [ ] Graph-based thinking (debtors/creditors as nodes)
- [ ] Greedy algorithms for transaction minimization

**Checkpoint**: Implement debt calculation logic in isolation (no UI)

---

## **Phase 8: Build the Split Bill App** (3-4 weeks)

### Week 21-22: Core Features
- [ ] People management screen + database
- [ ] Add/Edit expense screen
- [ ] Dashboard with expense list
- [ ] Debt calculation use case
- [ ] Navigation between screens

### Week 23: Advanced Features (This Project)
- [ ] Equal/Unequal split toggle
- [ ] Settlement tracking (`isSettled` flag)
- [ ] Modify existing expenses
- [ ] Dialog for expense actions

### Week 24: Polish & Testing
- [ ] Unit tests for use cases
- [ ] UI testing (optional)
- [ ] Bug fixes, edge cases
- [ ] Material Design polish

---

## **Phase 9: Deployment & Beyond** (1 week)

### Week 25: Release
- [ ] Generate signed APK
- [ ] Version control with Git
- [ ] Write README for GitHub
- [ ] (Optional) Publish to Play Store

---

## **⏱️ Kotlin/Android Time Estimate**

- **Absolute beginner (no programming)**: ~6 months
- **Know programming, new to Kotlin/Android**: ~3-4 months
- **Know Kotlin, new to Compose/Android**: ~2-3 months
- **Know Android basics**: ~1-2 months

---

## **🎯 Skills Breakdown for Kotlin/Android**

| Skill | Used In | Priority |
|-------|---------|----------|
| Kotlin basics | Everything | ⭐⭐⭐⭐⭐ |
| Jetpack Compose | All UI screens | ⭐⭐⭐⭐⭐ |
| Room Database | Expenses + People storage | ⭐⭐⭐⭐ |
| MVVM Architecture | Code structure | ⭐⭐⭐⭐ |
| Hilt | DI for ViewModels, Repository | ⭐⭐⭐⭐ |
| Coroutines & Flow | Async DB operations | ⭐⭐⭐⭐ |
| Navigation Compose | Multi-screen routing | ⭐⭐⭐ |
| Algorithms | Debt simplification | ⭐⭐⭐ |
| Testing | Unit tests (optional) | ⭐⭐ |

---

# Flutter Roadmap

## **Phase 1: Dart Programming Fundamentals** (2-3 weeks)

### Week 1-2: Dart Basics
- [ ] Variables, data types, null safety (`?`, `!`, `??`)
- [ ] Functions, arrow functions, named parameters
- [ ] Classes, constructors, getters/setters
- [ ] Collections (List, Map, Set) and methods
- [ ] Control flow (if/else, switch, loops)
- [ ] `final` vs `const`

**Resources**: 
- [DartPad](https://dartpad.dev/)
- [Dart Language Tour](https://dart.dev/guides/language/language-tour)

### Week 3: Advanced Dart
- [ ] Async/await and Futures
- [ ] Streams (single subscription vs broadcast)
- [ ] Extension methods
- [ ] Mixins
- [ ] JSON serialization/deserialization
- [ ] Generics

**Checkpoint**: Build a CLI expense calculator in Dart

---

## **Phase 2: Flutter Basics** (3-4 weeks)

### Week 4-5: Core Flutter Concepts
- [ ] Widget tree (Everything is a Widget!)
- [ ] Stateless vs Stateful widgets
- [ ] `setState()` for local state
- [ ] BuildContext
- [ ] Widget lifecycle (`initState`, `dispose`)
- [ ] Hot reload vs Hot restart

### Week 6-7: Layout & Styling
- [ ] Core layouts: Column, Row, Stack, Container
- [ ] ListView & ListView.builder
- [ ] Padding, Margin, SizedBox
- [ ] Material Design widgets (Scaffold, AppBar, FloatingActionButton)
- [ ] TextField, Button variations
- [ ] Theme customization (colors, typography)

**Resources**: 
- [Flutter Codelab](https://docs.flutter.dev/get-started/codelab)
- [Flutter Widget Catalog](https://docs.flutter.dev/ui/widgets)

**Checkpoint**: Build a static UI mockup of the Split Bill app (no functionality)

---

## **Phase 3: Navigation & Routing** (1 week)

### Week 8: Navigation
- [ ] Navigator.push / Navigator.pop
- [ ] Named routes
- [ ] Passing data between screens
- [ ] **go_router** package (recommended for modern apps)
  - [ ] Route definitions
  - [ ] Path parameters (`/expense/:id`)
  - [ ] Deep linking basics

**Resources**:
- [go_router Package](https://pub.dev/packages/go_router)

**Checkpoint**: Multi-screen app with navigation between People, Dashboard, Add Expense

---

## **Phase 4: State Management** (2-3 weeks)

### Week 9-10: Provider (Simpler Option)
- [ ] ChangeNotifier pattern
- [ ] Provider, ChangeNotifierProvider
- [ ] Consumer vs Selector
- [ ] MultiProvider for multiple providers
- [ ] When to use Provider vs Riverpod

### Week 11: Riverpod (Advanced, Recommended)
- [ ] StateProvider, StateNotifierProvider
- [ ] FutureProvider, StreamProvider
- [ ] Riverpod hooks
- [ ] Auto-dispose
- [ ] Family modifiers for parameterized providers

**For this project**: Use **Riverpod** (better for scalability)

**Resources**: 
- [Riverpod Documentation](https://riverpod.dev/)
- [Provider Package](https://pub.dev/packages/provider)

**Checkpoint**: Refactor static UI to use state management for expense list

---

## **Phase 5: Local Database Storage** (2 weeks)

### Option A: Hive (Simpler, Recommended for this project)

#### Week 12-13: Hive Basics
- [ ] Hive vs SQLite (when to use what)
- [ ] TypeAdapters for custom objects
- [ ] Box operations (put, get, delete)
- [ ] Lazy boxes for large data
- [ ] Streams for reactive updates (`box.watch()`)

**Why Hive for this project**: 
- ✅ No schema migrations needed
- ✅ Direct Map/List storage (no serialization hell)
- ✅ Faster for simple CRUD operations

**Example Code**:
```dart
@HiveType(typeId: 0)
class Expense extends HiveObject {
  @HiveField(0)
  String description;
  
  @HiveField(1)
  double amount;
  
  @HiveField(2)
  Map<int, double> shares; // Works directly!
}
```

### Option B: SQLite (If you want SQL)
- [ ] sqflite package
- [ ] Creating tables, migrations
- [ ] Raw queries vs query builders
- [ ] Foreign keys, relationships

**Resources**: 
- [Hive Documentation](https://docs.hivedb.dev/)
- [sqflite Guide](https://pub.dev/packages/sqflite)

**Checkpoint**: Store and retrieve expenses from Hive

---

## **Phase 6: Architecture & Code Organization** (1-2 weeks)

### Week 14-15: Clean Architecture (Flutter Style)
- [ ] **Feature-first folder structure**:
  ```
  lib/
    features/
      expenses/
        domain/models/
        data/repositories/
        presentation/screens/
      people/
        ...
  ```
- [ ] Repository pattern
- [ ] Use case classes (optional, can be overkill)
- [ ] Separation: UI → Repository → Data Source

**No DI framework needed!** Flutter's `Provider`/`Riverpod` handles it.

**Checkpoint**: Organize your code into clean layers

---

## **Phase 7: Advanced UI & Forms** (2 weeks)

### Week 16-17: Forms & Validation
- [ ] Form widget & GlobalKey
- [ ] TextFormField with validators
- [ ] Focus management
- [ ] Custom input formatters (for currency)
- [ ] Autocomplete widget (for payer name)

### Advanced Widgets for This Project
- [ ] AlertDialog for "Modify/Completed" options
- [ ] Switch/Toggle for Equal/Unequal split
- [ ] Custom ListTiles
- [ ] Dismissible for swipe-to-delete (optional)
- [ ] AnimatedSwitcher for smooth transitions

**Checkpoint**: Fully functional Add/Edit Expense screen

---

## **Phase 8: Algorithm Implementation** (1 week)

### Week 18: Debt Simplification Logic
- [ ] Same algorithm as Kotlin version (language-agnostic)
- [ ] Calculate balances (paid - consumed)
- [ ] Separate debtors and creditors
- [ ] Greedy pairing algorithm
- [ ] Handle floating-point precision

**This is identical logic**, just in Dart syntax!

**Checkpoint**: Unit test the debt algorithm in isolation

---

## **Phase 9: Build the Split Bill App** (3 weeks)

### Week 19-20: Core Features
- [ ] People management screen + Hive storage
- [ ] Dashboard with total spending
- [ ] Add expense with equal split
- [ ] Debt calculation display
- [ ] Navigation flow

### Week 21: Advanced Features
- [ ] Unequal split with per-person amount input
- [ ] Edit existing expenses
- [ ] Settlement tracking (`isSettled` flag)
- [ ] Dialog for expense actions (Modify/Complete)
- [ ] Validation: total allocated = expense amount

---

## **Phase 10: Polish & Testing** (1-2 weeks)

### Week 22-23: Final Touches
- [ ] Theming (dark mode support)
- [ ] Error handling & snackbars
- [ ] Empty states ("No expenses yet")
- [ ] Loading indicators
- [ ] Unit tests for core logic
- [ ] Widget tests (optional)

---

## **Phase 11: Deployment** (1 week)

### Week 24: Release
- [ ] Build APK (`flutter build apk`)
- [ ] (Optional) Build iOS app
- [ ] Git repository + README
- [ ] (Optional) Deploy to Play Store / App Store
- [ ] (Bonus) Web version (`flutter build web`)

---

## **⏱️ Flutter Time Estimate**

- **Absolute beginner (no programming)**: ~5 months
- **Know programming, new to Dart/Flutter**: ~2.5-3 months
- **Know Dart, new to Flutter**: ~1.5-2 months
- **Know Flutter basics**: ~1 month

**Flutter is ~20-25% faster to learn** than Kotlin/Compose because:
- Simpler architecture (no complex DI)
- Easier database (Hive = no migrations)
- Hot reload speeds up iteration

---

## **🎯 Skills Breakdown for Flutter**

| Skill | Used In | Priority |
|-------|---------|----------|
| Dart fundamentals | Everything | ⭐⭐⭐⭐⭐ |
| Flutter Widgets | All UI screens | ⭐⭐⭐⭐⭐ |
| State Management (Riverpod) | Expense/People state | ⭐⭐⭐⭐⭐ |
| Hive Database | Local storage | ⭐⭐⭐⭐ |
| Navigation (go_router) | Multi-screen app | ⭐⭐⭐⭐ |
| Forms & Validation | Add/Edit screens | ⭐⭐⭐ |
| Async/Streams | Reactive data | ⭐⭐⭐ |
| Algorithms | Debt simplification | ⭐⭐⭐ |
| Testing | Unit tests | ⭐⭐ |

---

# Framework Comparison

## **Kotlin/Compose vs Flutter**

| Aspect | Kotlin/Compose | Flutter |
|--------|---------------|---------|
| **Language** | Kotlin | Dart |
| **Database** | Room (complex, migrations required) | Hive (simple, no migrations) |
| **Dependency Injection** | Hilt (requires setup) | Built into Riverpod/Provider |
| **State Management** | StateFlow + collectAsState | ChangeNotifier / StateProvider |
| **Navigation** | NavHost with typed arguments | go_router with path params |
| **Learning Curve** | Steeper | Gentler |
| **Code Volume** | ~20-30% more | ~20-30% less |
| **Platform Support** | Android only | Android, iOS, Web, Desktop |
| **Performance** | Native (fastest) | Near-native |
| **Hot Reload** | Good | Excellent |
| **Community** | Large (Android) | Large (Cross-platform) |
| **Job Market** | Strong (Android dev) | Growing (Cross-platform) |

### **When to Choose Kotlin/Compose**
- ✅ Android-only app
- ✅ Maximum performance needed
- ✅ Latest Android features required
- ✅ Team knows Kotlin already

### **When to Choose Flutter**
- ✅ Cross-platform (iOS + Android + Web)
- ✅ Faster development time
- ✅ Simpler codebase
- ✅ Easier to learn

---

# Resources Summary

## **Kotlin/Android Resources**

| Topic | Resource |
|-------|----------|
| Kotlin | [Kotlin Bootcamp by Google](https://developer.android.com/courses/kotlin-bootcamp/overview) |
| Compose | [Jetpack Compose Pathway](https://developer.android.com/courses/pathways/compose) |
| MVVM | [Android Architecture Guide](https://developer.android.com/topic/architecture) |
| Room | [Room Codelab](https://developer.android.com/codelabs/android-room-with-a-view-kotlin) |
| Hilt | [Using Hilt in Android](https://developer.android.com/training/dependency-injection/hilt-android) |
| Coroutines | [Coroutines Codelab](https://developer.android.com/codelabs/kotlin-coroutines) |
| Testing | [Android Testing](https://developer.android.com/training/testing) |

## **Flutter Resources**

| Topic | Resource |
|-------|----------|
| Dart | [Dart Language Tour](https://dart.dev/guides/language/language-tour) |
| Flutter Basics | [Flutter Codelabs](https://docs.flutter.dev/codelabs) |
| State Management | [Riverpod Documentation](https://riverpod.dev/) |
| Hive | [Hive Docs](https://docs.hivedb.dev/) |
| Navigation | [go_router Package](https://pub.dev/packages/go_router) |
| Best Practices | [Effective Dart](https://dart.dev/guides/language/effective-dart) |
| UI Widgets | [Flutter Widget Catalog](https://docs.flutter.dev/ui/widgets) |

## **YouTube Channels**

### Kotlin/Android
- [Philipp Lackner](https://www.youtube.com/@PhilippLackner) - Modern Android development
- [Coding in Flow](https://www.youtube.com/@codinginflow) - Android tutorials
- [Android Developers](https://www.youtube.com/@AndroidDevelopers) - Official channel

### Flutter
- [The Flutter Way](https://www.youtube.com/@TheFlutterWay)
- [FlutterMapp](https://www.youtube.com/@fluttermapp)
- [Reso Coder](https://www.youtube.com/@ResoCoder)

## **Online Communities**

- **Reddit**: r/androiddev, r/FlutterDev
- **Discord**: Flutter Community, Android Dev
- **Stack Overflow**: Tags `android`, `jetpack-compose`, `flutter`, `dart`

---

# Code Examples

## **Kotlin/Compose Example**

### Expense Model (Room)
```kotlin
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
    val amount: Double,
    val payerId: Long,
    val shares: String, // Serialized as "id:amount,id:amount"
    val isSettled: Boolean = false
)
```

### ViewModel
```kotlin
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: SplitBillRepository
) : ViewModel() {
    val uiState: StateFlow<DashboardUiState> = combine(
        repository.getAllExpenses(),
        repository.getAllPeople()
    ) { expenses, people ->
        // Calculate UI state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardUiState())
}
```

### UI
```kotlin
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = { TopAppBar(title = { Text("Split App") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(uiState.expenses) { expense ->
                ExpenseCard(expense = expense)
            }
        }
    }
}
```

---

## **Flutter Example**

### Expense Model (Hive)
```dart
@HiveType(typeId: 0)
class Expense extends HiveObject {
  @HiveField(0)
  late String description;
  
  @HiveField(1)
  late double amount;
  
  @HiveField(2)
  late int payerId;
  
  @HiveField(3)
  late Map<int, double> shares; // Direct Map support!
  
  @HiveField(4)
  late bool isSettled;
}
```

### State Management (Riverpod)
```dart
final expenseProvider = StateNotifierProvider<ExpenseNotifier, List<Expense>>((ref) {
  return ExpenseNotifier();
});

class ExpenseNotifier extends StateNotifier<List<Expense>> {
  ExpenseNotifier() : super([]);
  
  void addExpense(Expense expense) {
    state = [...state, expense];
  }
  
  void updateExpense(Expense expense) {
    state = [
      for (final e in state)
        if (e.id == expense.id) expense else e,
    ];
  }
}
```

### UI Widget
```dart
class DashboardScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final expenses = ref.watch(expenseProvider);
    
    return Scaffold(
      appBar: AppBar(title: Text('Split App')),
      body: ListView.builder(
        itemCount: expenses.length,
        itemBuilder: (context, index) {
          return ExpenseCard(expense: expenses[index]);
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.pushNamed(context, '/add-expense'),
        child: Icon(Icons.add),
      ),
    );
  }
}
```

---

# Final Tips

## **For Kotlin/Compose**
1. ✅ Master Kotlin first before diving into Compose
2. ✅ Understand MVVM architecture deeply
3. ✅ Learn Room migrations early to avoid database crashes
4. ✅ Use Hilt from the start to avoid refactoring later
5. ✅ Practice with small projects before the main app

## **For Flutter**
1. ✅ Dart is easier than Kotlin—spend less time here
2. ✅ Hot reload is your best friend—use it constantly
3. ✅ Start with Provider, move to Riverpod when comfortable
4. ✅ Hive is perfect for this app—don't overcomplicate with SQLite
5. ✅ Build UI first, add functionality later

## **General Advice**
- 🎯 Build checkpoints projects—don't skip them
- 📚 Documentation > Random tutorials
- 💪 Struggle is learning—don't copy-paste blindly
- 🔄 Refactor as you learn better patterns
- 🧪 Write tests for complex logic (debt algorithm)
- 🐛 Debug methodically—read error messages carefully
- 🌟 Commit to Git frequently

---

**Good luck on your learning journey! Both paths lead to the same destination—a fully functional Split Bill Calculator. Choose based on your goals and enjoy the process! 🚀**
