# 📦 Gilded Rose Inventory System (Spring Boot)

This is a Spring Boot application for managing inventory items based on the Gilded Rose kata. It uses the State design pattern to handle different product behaviors like Aged Brie, Sulfuras, Backstage Passes, and Conjured items.

---

## 🚀 Getting Started

### ✅ Prerequisites

Ensure you have the following installed:

- **Java 17** or later
- **Maven 3.8+**
- (Optional) Postman or a front-end to test APIs
- Data will be stored on H2 in-memory database

---

### ▶️ Running the Application

#### Step 1: Clone the Repository

```bash
git clone https://github.com/gebremso/gilded-rose-exercise.git
cd gilded-rose-exercise
```
#### Step 2: Build the Application
```bash
mvn clean install
```

#### Step 3: Run the Application
```bash
mvn spring-boot:run
```

The app should be available at:
```bash
localhost:8080/
```

### API EndPoints
| Method | URL                              | Description                    |
| ------ |----------------------------------|--------------------------------|
| POST   | `/api/addItem`                   | Add a new item (via JSON body) |
| GET    | `/api/items`                     | Get all items                  |
| GET    | `/api/itemsAsOf?date=YYYY-MM-DD` | Filter items by date           |
| GET    | `/api/item/{id}`                 | Get item based on id           |


### UI Features
The application includes a simple front-end interface that interacts with the backend APIs using HTML and JavaScript (Bootstrap,Fetch API and jQuery). Below are the key features of the UI:

#### Manage Item Page
**Purpose**:  
Allows users to submit a new product/item to the inventory.

**Features**:
- A user-friendly HTML form with inputs for:
    - **Product name** (e.g., `"Aged Brie"`, `"Sulfuras"`)
    - **Sell-in days** (integer)
    - **Quality** (integer, range: 0–50)
- Form validation to ensure inputs are not empty and within valid ranges.
- JavaScript function that sends a `POST` request to `/api/addItem` using JSON format.

#### View Items Page
**Purpose**:  
Enables users to filter and view products in the inventory based on a specific date (e.g., purchase date or snapshot date).

**Features**:
- A **date picker field** (`<input type="date">`) to select the desired date.
- A **"Load Items"** button that fetches items matching the selected date.
- A **dynamic table** that renders the filtered items with columns for:
    - **Name**
    - **Sell-In**
    - **Quality**