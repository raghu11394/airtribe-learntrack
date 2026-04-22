# Design Notes

## Why ArrayList Instead of Array?

We use `ArrayList` instead of plain arrays because:

- **Dynamic sizing** – Arrays have a fixed size defined at creation. With `ArrayList`, we can add and remove students, courses, and enrollments without worrying about capacity limits or manual resizing.
- **Built-in methods** – `ArrayList` provides convenient methods like `add()`, `remove()`, `size()`, and `isEmpty()` that simplify data management.
- **Type safety with generics** – `ArrayList<Student>` ensures only `Student` objects are stored, catching type errors at compile time.

For a management system where the number of records is unknown and changes at runtime, `ArrayList` is the natural choice.

## Where Static Members Are Used and Why

- **`IdGenerator`** – Uses `static` fields (`studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter`) and `static` methods (`getNextStudentId()`, etc.). These counters must be shared across all instances and persist throughout the application's lifetime. Making them static ensures a single, global counter per entity type.
- **`InputValidator`** – All methods are `static` because they are pure utility functions that don't depend on any instance state. Calling `InputValidator.isNullOrEmpty(value)` is cleaner than creating an object first.
- **`AppConstants` and `MenuOptions`** – Use `static final` fields for application-wide constants. These values never change and should be accessible without instantiation.

## Where Inheritance Is Used and What We Gained

- **`Person` → `Student`** – `Student` extends `Person`, inheriting common fields (`id`, `firstName`, `lastName`, `email`) and methods. This avoids duplicating those fields in `Student`.
- **Constructor chaining with `super`** – `Student` constructors call `super(...)` to initialize the parent fields, demonstrating proper use of inheritance in constructors.
- **Method overriding (`getDisplayName()`)** – `Person` provides a base implementation returning `"firstName lastName"`. `Student` overrides it to include batch information: `"firstName lastName [Batch: X]"`. This demonstrates **polymorphism** — the same method name behaves differently based on the actual object type.

## Separation of Concerns

The project follows a layered architecture:

| Layer        | Package      | Responsibility                          |
|--------------|-------------|------------------------------------------|
| **Entity**   | `entity/`   | Data models with encapsulated fields     |
| **Repository** | `repository/` | In-memory data storage (ArrayList)    |
| **Service**  | `service/`  | Business logic and validation            |
| **UI**       | `Main.java` | Console menu, user input, display output |

This separation means `Main.java` only handles displaying menus and reading input — all business rules live in the service layer, and data access is handled by repositories. Each layer can be modified independently.
