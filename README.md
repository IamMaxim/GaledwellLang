# GaledwellLang
#### `Parser`
Obviously, a source code parser
#### `Main`
Class to start parsing and execution
#### `Runtime`
This class holds all info about current run

## Types
#### `Type`
Base class for all types
#### `TypeInt`
Class that stores integer numbers
#### `TypeFloat`
Class that stores floating-point numbers
#### `TypeString`
Class that stores strings
#### `Function`
Yep, functions are variables too :)
#### `Class`
And classes too
#### `VariableStorage`
This thing extends Class, in future it will serialize its data

## Functions
In this package "native" functions are stored. This means they are written in interpreter language and bound to scripting language.
Also contains `FunctionParsed` which is function that loads from scripts

## Operations
This package contains operations, such as assigning, calling functions etc.
