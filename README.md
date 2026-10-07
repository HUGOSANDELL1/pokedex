# Pokedex - Pokemon Battler

This is a console application made in Java where the user can manage Pokemon and their attacks and also battle against randomly selected wild Pokemon.

The project is built with Java 21 and Maven and uses Jackson for JSON persistence.

## Features

The user can:

- View all Pokemon
- Add Pokemon
- Edit Pokemon
- Remove Pokemon
- Save Pokemon
- Load Pokemon
- Reset the Pokedex
- Search Pokemon
- Filter Pokemon by type
- Sort Pokemon by name
- View Pokedex statistics
- Start a Pokemon battle
- View persistent battle results

Pokemon and battle statistics are saved in JSON files.

## How to run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Make sure Java 21 is installed.
4. Let Maven load the dependencies.
5. Run Main.java.
6. Use the numbered menu in the console.

## Example

When the program starts, the user gets a menu:

1. View all Pokemon
2. Add Pokemon
3. Edit Pokemon
4. Remove Pokemon
5. Save
6. Load
7. Reset
8. Search Pokemon
9. Filter by type
10. Sort by name
11. Statistics
12. Start Battle
13. Battle Results
14. Exit

## Battle system

When a battle starts, the player selects one of their Pokemon.

A random wild Pokemon is then selected as the opponent.

At the start of the battle, the program randomly decides which Pokemon attacks first. The same Pokemon continues to attack first during every round of that battle.

The player chooses an attack from the selected Pokemon's attack list.

The CPU chooses one of its attacks randomly.

An attack can either hit or miss depending on its accuracy.

A Pokemon is defeated when its current HP reaches 0.

The battle ends immediately when one Pokemon is defeated.

## Damage formula

The battle system calculates damage using:

`baseDamage * typeEffectiveness * randomFactor * criticalMultiplier`

The random damage factor is between 0.85 and 1.00.

Critical hits have a 1 in 16 chance and use a 2.0 damage multiplier.

Critical hits are shown in the battle log.

## Type effectiveness

The battle system uses type effectiveness.

Examples:

- FIRE -> GRASS = 2.0x
- WATER -> FIRE = 2.0x
- GRASS -> WATER = 2.0x
- ELECTRIC -> WATER = 2.0x
- FIRE -> WATER = 0.5x
- WATER -> GRASS = 0.5x
- GRASS -> FIRE = 0.5x
- NORMAL attacks use 1.0x unless another rule applies

The battle log shows when an attack is super effective or not very effective.

## OOP structure

The project is divided into packages based on responsibility.

### model

Contains the main domain objects.

Examples:

- `Pokemon`
- `Attack`
- `DamageAttack`
- `DoubleStrikeAttack`
- `Type`
- `BattleStats`

### repository

Handles JSON persistence.

Examples:

- `PokemonFileManager`
- `BattleStatsFileManager`

### service

Contains application and battle logic.

Examples:

- `BattleEngine`
- `PokemonService`
- `PokedexService`
- `PokemonSeeder`
- `InputHelper`

### ui

Contains the console menu.

- `PokedexMenu`

This separation makes the project easier to maintain and keeps persistence, application logic, model classes and user interface separated.

## Polymorphism

The project uses polymorphism in the attack system.

`Attack` is an abstract base class.

Concrete attack classes extend `Attack`, for example:

- `DamageAttack`
- `DoubleStrikeAttack`

Each subclass overrides the `execute()` method with its own behavior.

The battle engine works with references of type `Attack` and calls:

`attack.execute(...)`

The battle engine does not need to check which concrete attack class is being used.

Java selects the correct `execute()` implementation at runtime.

For example, a normal `DamageAttack` applies the calculated damage once, while `DoubleStrikeAttack` applies the same total calculated damage through two separate hits.

This makes it possible to add new attack behaviors without rewriting the battle loop.

## Encapsulation

The project uses private fields and public methods to control access to object data.

Pokemon damage is handled through the `takeDamage()` method so current HP cannot go below 0.

Input and constructor validation are also used to prevent invalid objects and values.

## JSON persistence

Jackson is used to save and load JSON data.

Pokemon are stored in:

`pokemon.json`

Battle statistics are stored in:

`battle-stats.json`

Battle statistics remain available after restarting the program.

The attack hierarchy also supports JSON loading of concrete attack subclasses.

## Battle statistics

The program stores persistent battle statistics including:

- Total wins
- Total losses
- Wins per Pokemon
- Losses per Pokemon
- Win ratio per Pokemon
- Most used Pokemon
- Most used attack

Example:

`Bulbasaur - Wins: 2, Losses: 1, Win ratio: 66.7%`

## Input validation and crash safety

The application validates console input and prevents invalid menu and attack choices from crashing the program.

Examples of handled input include:

- Letters instead of numbers
- Numbers outside the allowed menu range
- Invalid attack indexes
- Pokemon with 0 HP
- Pokemon without attacks
- Missing JSON files
- Corrupted JSON files
- Input ending unexpectedly

The application displays an error message or returns safely instead of crashing.

## Limitations

The battle system currently focuses mainly on damage-based attacks.

More attack subclasses and battle effects could be added later, such as status effects, healing attacks or temporary stat changes.

Pokemon HP is persistent during the running application and can remain reduced after a battle until the Pokedex is reset or the data is changed.

## Technologies

- Java 21
- Maven
- Jackson
- IntelliJ IDEA
- Git
- GitHub



