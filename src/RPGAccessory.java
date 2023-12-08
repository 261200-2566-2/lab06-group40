public class RPGAccessory {
    protected int level;
    protected boolean equipped;
        /**
         * Preconditions: None
         * Postconditions: increase accessory lv.
         * Side Effects: None
         */
    public void levelUp() {
        level++;
    }
        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: print accessory's level.
         */
    void showLevel(){}
    
    double calculateRunSpeedDecrease(int characterLevel) {
        return 0;
    }
        /**
         * Preconditions: -
         * Postconditions: -
         * Side Effects: print stat.
         */
    void showStatus() {}

        /**
         * Preconditions: - 
         * Postconditions: -
         * Side Effects: print item is equipped or not.
         */
    public void isEquipped() {
        if(equipped){
            System.out.println("This item is equipped");
        }else{
            System.out.println("This item is unequipped");
        }
    }


    public static class Helmet extends RPGAccessory {

        public Helmet() {
            this.level = 1; // Default level is 1 for a new helmet.
            this.equipped = false; // Default state is not equipped.
        }
        @Override
        public void showLevel() {
            System.out.println("Helmet Level: " + level);
        }
        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {

            return 0.05 * level * characterLevel;
        }

        @Override
        public void showStatus() {
            System.out.println("Helmet Status - Equipped: " + equipped);
            showLevel();
        }

        /**
         * Preconditions: None
         * Postconditions: None
         * Side Effects: random value of crit damage.
         */
        public boolean RandomCritDamage(){
            int min = 1;
            int max = 100;
            int random_int = (int) Math.floor(Math.random() *(max - min + 1) + min);
            if(random_int%2 == 0){
                return false;
            }else{
                return true;
            }
        }

        /**
         * Preconditions: is boolen value.
         * Postconditions: Return calculated damage multiplier based on crit.
         * Side Effects: None
         */
        public double CalculatedCritDamage(boolean check){
            if(check){
                return 1.50;
            }else{
                return 1.0;
            }
        }



    }

    // Concrete Boots Accessory
    public static class SpeedyBoots extends RPGAccessory {
        public SpeedyBoots() {
            this.level = 1;
            this.equipped = false;
        }
        @Override
        public void showLevel() {
            System.out.println("Boots Level: " + level);
        }
        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {

            return 0.08 * level * characterLevel;
        }
        @Override
        public void showStatus() {
            System.out.println("Boots Status - Equipped: " + equipped);
            showLevel();
        }
        
        /**
         * Preconditions: k > 2.
         * Postconditions: return boolean.
         * Side Effects: None
         */
        public boolean DoubleAttack(int k){
            if(k >= 3){
                return true;
            }else{
                return false;
            }
        }

        /**
         * Preconditions: t > 1.
         * Postconditions: return boolean
         * Side Effects: -
         */
        public boolean boostSpeed(int t){
            if(t >= 2){
                return true;
            }else{
                return false;
            }
        }

    }

    public static class Bracelets extends RPGAccessory {
        public Bracelets(){
            this.level = 1;
            this.equipped = false;
        }
        @Override
        public void showLevel() {
            System.out.println("Bracelet Level: " + level);
        }

        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {

            return 0.03 * level * characterLevel;
        }

        @Override
        public void showStatus() {
            System.out.println("Bracelet Status - Equipped: " + equipped);
            showLevel();
        }

        /**
         * Preconditions: Maxhp*0.5 >= currenthp .
         * Postconditions: return boolean
         * Side Effects: -
         */
        public boolean Protectedlife(int MaxHp, int currenthp){
            if(currenthp <= MaxHp*0.5){
                return true;
            }else{
                return false;
            }
        }

        /**
         * Preconditions: MaxMana* >= currentmana.
         * Postconditions: return boolean.
         * Side Effects: -
         */
        public boolean ManaBoost(int MaxMana, int currentmana){
            if(currentmana <= (MaxMana*0.8)){
                return true;
            }else{
                return false;
            }
        }
    }

    public static class Gauntlets extends RPGAccessory {
        public Gauntlets(){
            this.level = 1;
            this.equipped = false;
        }
        @Override
        public void showLevel() {
            System.out.println("Gauntlet Level: " + level);
        }

        @Override
        public double calculateRunSpeedDecrease(int characterLevel) {

            return 0.09 * level * characterLevel;
        }

        @Override
        public void showStatus() {
            System.out.println("Gauntlets Status - Equipped: " + equipped);
            showLevel();
        }

        /**
         * Preconditions: currentEnemyHp and maxEnemyHp are non-negative int.
         * Postconditions: return 1.5
         * Side Effects: -
         */
        public double BoostAttack(int maxEnemyHp, int currentEnemyHp){
            if(currentEnemyHp <= maxEnemyHp){
                return 1.5;
            }else{
                return 1.0;
            }
        }

        /**
         * Preconditions: count >=3.
         * Postconditions: return boolean
         * Side Effects: -
         */
        public boolean StackAttacked(int count){
            if(count >= 3){
                return true;
            }else{
                return false;
            }
        }
    }

}
