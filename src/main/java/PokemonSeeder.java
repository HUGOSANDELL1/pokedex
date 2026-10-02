import java.util.ArrayList;

public class PokemonSeeder {

    public static void addSeedPokemon(ArrayList<Pokemon> pokemons) {


          Pokemon frallis = new Pokemon(
                    "Frallis",
                    Type.FIRE,
                    1000
            );

            Attack klovenCurse = new Attack(
                    "Kloven Curse",
                    33,
                    67,
                    Type.FIRE
            );

            Attack frallisCharm = new Attack(
                    "Frallis Charm",
                    39,
                    69,
                    Type.ELECTRIC
            );

            Attack mysschock = new Attack(
                    "Mys schock",
                    97,
                    99,
                    Type.ELECTRIC
            );

            Attack snailMode = new Attack(
                    "Snail Mode",
                    10,
                    100,
                    Type.NORMAL
            );

            frallis.addAttack(snailMode);
            frallis.addAttack(mysschock);
            frallis.addAttack(frallisCharm);
            frallis.addAttack(klovenCurse);

            pokemons.add(frallis);

            Pokemon charmander = new Pokemon(
                    "Charmander",
                    Type.FIRE,
                    90
            );

            Attack ember = new Attack(
                    "Ember",
                    35,
                    90,
                    Type.FIRE
            );

            charmander.addAttack(ember);

            pokemons.add(charmander);

            Pokemon squirtle = new Pokemon(
                    "Squirtle",
                    Type.WATER,
                    95
            );

            Attack waterGun = new Attack(
                    "Water Gun",
                    35,
                    95,
                    Type.WATER
            );

            squirtle.addAttack(waterGun);

            pokemons.add(squirtle);

            Pokemon snorlax = new Pokemon(
                    "Snorlax",
                    Type.NORMAL,
                    160
            );

            Attack bodySlam = new Attack(
                    "Body Slam",
                    50,
                    85,
                    Type.NORMAL
            );

            snorlax.addAttack(bodySlam);

            pokemons.add(snorlax);

            Pokemon pikachu = new Pokemon(
                    "Pikachu",
                    Type.ELECTRIC,
                    100
            );

            Attack thunderShock = new Attack(
                    "Thunder Shock",
                    40,
                    90,
                    Type.ELECTRIC
            );

            pikachu.addAttack(thunderShock);

            pokemons.add(pikachu);

            Pokemon bulbasaur = new Pokemon(
                    "Bulbasaur",
                    Type.GRASS,
                    90
            );

            Attack vineWhip = new Attack(
                    "Vine Whip",
                    35,
                    95,
                    Type.GRASS
            );

            bulbasaur.addAttack(vineWhip);

            pokemons.add(bulbasaur);

            Pokemon roffeMannen = new Pokemon(
                    "RoffeMannen",
                    Type.NORMAL,
                    120
            );

            Attack roffeSmash = new Attack(
                    "Roffe Smash",
                    45,
                    90,
                    Type.NORMAL
            );

            Attack sockSteal = new Attack(
                    "Sock Steal",
                    30,
                    95,
                    Type.NORMAL
            );

            Attack zoomies = new Attack(
                    "Zoomies",
                    25,
                    100,
                    Type.NORMAL
            );

            Attack stinkBomb = new Attack(
                    "Stink Bomb",
                    40,
                    85,
                    Type.NORMAL
            );

            roffeMannen.addAttack(roffeSmash);
            roffeMannen.addAttack(sockSteal);
            roffeMannen.addAttack(zoomies);
            roffeMannen.addAttack(stinkBomb);

            pokemons.add(roffeMannen);

            Pokemon ivyCow = new Pokemon(
                    "Ivy Cow",
                    Type.GRASS,
                    110
            );

            Attack grassAttack = new Attack(
                    "Grass Attack",
                    35,
                    95,
                    Type.GRASS
            );

            Attack salamanderAttack = new Attack(
                    "Salamander Attack",
                    40,
                    90,
                    Type.FIRE
            );

            Attack cinnamonbunRoll = new Attack(
                    "Cinnamonbun Roll",
                    30,
                    100,
                    Type.NORMAL
            );

            ivyCow.addAttack(grassAttack);
            ivyCow.addAttack(salamanderAttack);
            ivyCow.addAttack(cinnamonbunRoll);

            pokemons.add(ivyCow);
        }
    }