# Split Bill Calculator

A modern, efficient Android application built with **Kotlin** and **Jetpack Compose** to help groups of friends, roommates, or colleagues split expenses and settle debts easily. 

Stop doing math on napkins—let the app handle "who owes who" with its smart debt simplification algorithm!

## Features

### 👥 People Management
- Add and manage a list of people involved in the group.
- Visual avatars for easy identification.

### 💰 Expense Tracking
- **Add Expenses**: Log expenses with a description, amount, and payer.
- **Flexible Splits**: 
    - **Equal Split**: Automatically divides the bill equally among selected members.
    - **Unequal Split**: Assign specific amounts to each person (e.g., if someone ordered more expensive items).
- **Edit Support**: Made a mistake? Modify existing expenses easily.
- **History**: View a list of all recent expenses.

### Smart Debt Calculation
- **Real-time Dashboard**: Instantly see the total group spending and active members.
- **Who Owes Who**: The app automatically calculates the net balance for each person and simplifies debts.
    - *Example*: If Alice owes Bob $10 and Bob owes Charlie $10, the app simplifies this to Alice owes Charlie $10.
- **Settlement**: Mark expenses as "Completed" once they are settled to clear them from the debt ledger.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Database**: Room (Local offline storage)
- **Asynchronous Processing**: Coroutines & Flows

## 📸 How It Works

1.  **Add People**: Start by adding the names of everyone in your group.
2.  **Log a Bill**: When someone pays, tap "Add Expense". Select who paid and who splits the bill.
3.  **View Debts**: Check the Dashboard to see the simplified "Who Owes Who" list.
4.  **Settle Up**: As people pay back their debts, you can mark transactions or expenses as settled (Feature in progress).

## TODO / Roadmap

- [ ] **Groups Support**: Create multiple independent groups.
- [ ] **History**: View a log of all settlements and payments.
- [ ] **Currency Support**: Support for multiple currencies.
- [ ] **Graphs & Analytics**: Visual breakdown of spending by category or person.
- [ ] **Cloud Sync**: Sync data across devices using Firebase or a backend.
