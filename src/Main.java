public class Main {

    public interface Character {
    
        /**
         * Preconditions: None
         * Postconditions: The character's level is increased by 1.
         * Side Effects: Updates the character's stats using updateStat().
         */
        void levelUp();
    
        /**
         * use for calculate dmg taken
         * Preconditions: None
         * Postconditions: The character's HP is reduced by the specified damage.
         *                If the resulting HP is less than 0, set it to 0.
         * Side Effects: None
         */
        void takeDamage(double dmg);
    
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: Prints the name of the character.
         */
        void showName();
    
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: Prints the character's level, HP, Mana, and run speed.
         */
        void showStatus();
    
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: Prints the character's current HP.
         */
        void showCurrentHP();
    
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: Prints character's current Mana.
         */
        void showCurrentMana();
    
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: Placeholder implementation for updating run speed. Prints a message.
         */
        void updateRunSpeed();
    
        /**
         * Preconditions: None
         * Postconditions: Updates the character's stats HP, Mana, and RunSpeed.
         * Side Effects: None
         */
        void updateStat();
    
        /**
         * Preconditions: None
         * Postconditions: Returns the calculated maximum run speed based on the character's level.
         * Side Effects: None
         */
        double calculateMaxSpeed();
    
        /**
         * Preconditions: The target character must not be null.
         * Postconditions: Deals damage to the target character.
         * Side Effects: Calls the target's takeDamage() method.
         */
        void attack(Character target);
    }

    //  Accessories interfaces

    public interface Accessory {
        /**
         * Preconditions: 
         * Postconditions: 
         * Side Effects: 
         */

        /**
         * Preconditions: None
         * Postconditions: increase the level of accessory by 1.
         * Side Effects: None
         */
        void levelUp();

        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: print level.
         */
        void showLevel();

        /**
         * Preconditions: is a non-negative int.
         * Postconditions: return calculated value spd decrease 
         *                 based on char and accessory level
         * Side Effects: None
         */
        double calculateRunSpeedDecrease(int characterLevel);

         /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: print stat is equipped and level
         */
        void showStatus();

         /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: None
         */
        boolean isEquipped();
    }

    // Concrete Mage Character
    public static class Mage implements Character {
        private String name;
        private int level;
        private int atk; //yung mai dai use
        private int maxHP;
        private int currentHP;
        private int maxMana;
        private int currentMana;
        private double baseRunSpeed;
        private double maxRunSpeed;//same as atk

        public Mage(String name, int level, int baseRunSpeed) {
            this.name = name;
            this.level = level;
            this.baseRunSpeed = baseRunSpeed;
            updateStat();
        }

        @Override
        public void levelUp() {
            level++;
            updateStat();
        }

        @Override
        public void takeDamage(double dmg) {
            // Placeholder implementation for taking damage
            currentHP -= dmg;
            if (currentHP < 0) {
                currentHP = 0;
            }
        }

        @Override
        public void showName() {
            System.out.println("Mage: " + name);
        }

        @Override
        public void showStatus() {
            System.out.println("Level: " + level);
            System.out.println("HP: " + currentHP + "/" + maxHP);
            System.out.println("Mana: " + currentMana + "/" + maxMana);
            System.out.println("Run Speed: " + calculateMaxSpeed());
        }

        @Override
        public void showCurrentHP() {
            System.out.println("Current HP: " + currentHP);
        }

        @Override
        public void showCurrentMana() {
            System.out.println("Current Mana: " + currentMana);
        }

        @Override
        public void updateRunSpeed() {
            // Placeholder implementation for updating run speed
            System.out.println("Run speed updated.");
        }

        @Override
        public void updateStat() {
            maxHP = 100 + 10 * level;
            maxMana = 50 + 20 * level;
            atk = 10;
            currentHP = maxHP;
            currentMana = maxMana;
            // Update other stats comming soon...
            maxRunSpeed = calculateMaxSpeed();
        }

        @Override
        public double calculateMaxSpeed() {
            return baseRunSpeed * (0.1 + 0.03 * level);
        }

        public void cast(String name,int mana){
            if(currentMana > mana){
                currentMana -= mana;
                System.out.println("Activate : "+name+"!!!");
            }else{System.out.println("not enough mana");}
        }

        @Override
        public void attack(Character target) {
        System.out.println(name + " casts a spell on ");
        // Add logic for dealing damage to the target character
        target.takeDamage(20); // Adjust damage as needed
    }
    }

    // Concrete Warrior Character
    public static class Warrior implements Character {
        private String name;
        private int level;
        private int atk;
        private int maxHP;
        private int maxMana;
        private int currentMana;
        private int currentHP;
        private double baseRunSpeed;
        private double maxRunSpeed;

        public Warrior(String name) {
            this.name = name;
            this.level = 1; // Default level is 1 for a new warrior
            this.baseRunSpeed = 7; // Default base run speed for a warrior
            updateStat();
        }

        @Override
        public void levelUp() {
            level++;
            updateStat();
        }

        @Override
        public void takeDamage(double dmg) {
            // implementation for taking damage
            currentHP -= dmg;
            if (currentHP < 0) {
                currentHP = 0;
            }
        }

        @Override
        public void showName() {
            System.out.println("Warrior: " + name);
        }

        @Override
        public void showStatus() {
            System.out.println("Level: " + level);
            System.out.println("HP: " + currentHP + "/" + maxHP);
            System.out.println("Mana: " + currentMana + "/" + maxMana);
            System.out.println("Run Speed: " + calculateMaxSpeed());
        }

        @Override
        public void showCurrentHP() {
            System.out.println("Current HP: " + currentHP);
        }

        @Override
        public void showCurrentMana() {
            // Warriors don't have mana, so this is a placeholder
            System.out.println("Warriors don't have mana.");
        }

        @Override
        public void updateRunSpeed() {
            // Placeholder implementation for updating run speed
            System.out.println("Run speed updated.");
        }

        @Override
        public void updateStat() {
            maxHP = 100 + 10 * level;
            maxMana = 10 + 5 * level;
            atk = 10;
            currentHP = maxHP;
            currentMana = maxMana;
            // Update other stats...
            maxRunSpeed = calculateMaxSpeed();
        }

        @Override
        public double calculateMaxSpeed() {
            return baseRunSpeed * (0.1 + 0.02 * level);
        }

        public void Hpbuff(){
            currentHP += 20; 
        }
        @Override
        public void attack(Character target) {
        System.out.println(name + " swings a sword at ");
        // Add logic for dealing damage to the target character
        target.takeDamage(30); // Adjust damage as needed
        }
    }

    // Concrete Helmets Accessory
    public static class Helmet implements Accessory {
        private int level;
        private boolean equipped;

        public Helmet() {
            this.level = 1; // Default level is 1 for a new helmet
            this.equipped = false; // Default state is not equipped
        }

        @Override
        public void levelUp() {
            level++;
        }

        @Override
        public void showLevel() {
            System.out.println("Helmet Level: " + level);
        }

        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {
            // Placeholder implementation for calculating run speed decrease for Helmets
            return 0.05 * level * characterLevel;
        }

        @Override
        public void showStatus() {
            System.out.println("Helmet Status - Equipped: " + equipped);
            showLevel();
        }

        @Override
        public boolean isEquipped() {
            return equipped;
        }
        
    }

    // Concrete Boots Accessory
    public static class SpeedyBoots implements Accessory {
        private int level;
        private boolean equipped;

        public SpeedyBoots() {
            this.level = 1; // Default level is 1 for new boots
            this.equipped = false; // Default state is not equipped
        }

        @Override
        public void levelUp() {
            level++;
        }

        @Override
        public void showLevel() {
            System.out.println("Boots Level: " + level);
        }

        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {
            // Placeholder implementation for calculating run speed decrease for Boots
            return 0.08 * level * characterLevel;
        }

        @Override
        public void showStatus() {
            System.out.println("Boots Status - Equipped: " + equipped);
            showLevel();
        }

        @Override
        public boolean isEquipped() {
            return equipped;
        }
    }

    // Lab05 class to demonstrate interactions
    public static class Lab05 {
        public static void main(String[] args) {
            // Create Mage character
            Character fireMage = new Mage("Mage", 1, 8);

            // Level up Mage and show status
            fireMage.levelUp();
            fireMage.showStatus();

            // Create Warrior character
            Character War1 = new Warrior("Warrior");

            // Level up Warrior and show status
            War1.levelUp();
            War1.showStatus();

            // Create Helmet accessory
            Accessory steelHelmets = new Helmet();

            // Level up Helmet and show status
            steelHelmets.levelUp();
            steelHelmets.showStatus();

            // Create SpeedyBoots accessory
            Accessory speedyBoots = new SpeedyBoots();

            // Level up SpeedyBoots and show status
            speedyBoots.levelUp();
            speedyBoots.showStatus();
            fireMage.attack(War1);
            War1.showStatus();
        }
    }
}