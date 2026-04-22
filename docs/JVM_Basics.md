# JVM Basics

## What is JDK, JRE, and JVM?

- **JDK (Java Development Kit)** – The complete toolkit for Java development. It includes the compiler (`javac`), debugger, and other tools needed to write and compile Java programs. It also contains the JRE.

- **JRE (Java Runtime Environment)** – The runtime environment needed to *run* compiled Java programs. It includes the JVM and the standard class libraries. If you only need to run Java applications (not develop them), the JRE is sufficient.

- **JVM (Java Virtual Machine)** – The virtual machine that actually executes Java bytecode. It is platform-specific (there are different JVM implementations for Windows, macOS, Linux), but it provides a uniform execution environment for Java programs.

**Relationship:** JDK ⊃ JRE ⊃ JVM

## What is Bytecode?

When you compile a Java source file (`.java`) using `javac`, it does not produce machine code specific to your operating system. Instead, it produces **bytecode** — an intermediate, platform-independent representation stored in `.class` files. The JVM then interprets or JIT-compiles this bytecode into native machine code at runtime.

## What Does "Write Once, Run Anywhere" Mean?

"Write Once, Run Anywhere" (WORA) is Java's core philosophy. Because Java code compiles to platform-independent bytecode (not native machine code), the same `.class` files can run on any device or operating system that has a compatible JVM installed. You write your program once on macOS, and it runs without changes on Windows or Linux — the JVM on each platform handles the translation to native instructions. This is what makes Java truly portable.
