# SLR Grammar Parser

This project defines an SLR (Simple LR) grammar and implements both a lexical analyzer and a syntactical analyzer using Java.

## Features

- **SLR Grammar Definition**: Define and process SLR grammars.
- **Lexical Analyzer**: Tokenize input strings based on the grammar.
- **Syntactical Analyzer**: Parse tokens and validate input strings against the defined grammar.

## Project Structure

- **Lexical Analyzer**: Responsible for breaking input strings into tokens.
- **Syntactical Analyzer**: Implements the SLR parsing algorithm to validate and parse input strings.
- **SLR Grammar Definition**: Encodes the rules and structure of the grammar to be parsed.

## Prerequisites

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) 11 or later.

## How to Use

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/eyabesbes/SLR-Grammar-Parser.git
   cd SLR-Grammar-Parser
   ```

2. **Compile the Java Files**:
   ```bash
   javac -d bin src/**/*.java
   ```

3. **Run the Application**:
   ```bash
   java -cp bin Main
   ```

4. **Input Your Grammar and Strings**:
   - Follow the prompts to input your grammar rules and validate strings.

## Example

1. Define a grammar:
   ```
   S -> A B
   A -> a
   B -> b
   ```

2. Input a string to parse:
   ```
   Input: ab
   ```

3. The program will validate whether the string conforms to the grammar.

## Contribution

Feel free to contribute to this project by submitting issues or pull requests. Contributions are welcome for the following:
- Enhancements to the grammar definition.
- Optimization of the lexical and syntactical analysis processes.
- Bug fixes or new features.


## Author

Developed by [Eya Besbes](https://github.com/eyabesbes).

## Acknowledgments

- Inspiration for the project came from compiler design concepts.
- Thanks to resources and guides on SLR grammar and Java programming.
