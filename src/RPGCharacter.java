import java.text.DecimalFormat;

public class RPGCharacter {
    protected String name;
    protected double atk;
    protected int def;
    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int maxMana;
    protected int currentMana;
    protected double baseRunSpeed;
    protected double maxRunSpeed;
    protected boolean equippedBoot = false;
    protected boolean equippedbracelet = false;
    protected boolean equippedHelmet = false;
    protected boolean equippedGauntlet = false;
    protected boolean CritOn = false;
    protected double Critdmg;
    protected boolean DoubleAttack = false;
    protected int countAtk;
    protected int beAttackedCount;
    protected boolean stackAttackedOn = false;
    protected boolean protectlife = false;
    protected int maxHpEnemy;
    protected int currentEnemyHp;
    /**
     * Increases the character's level by 1 and updates stats.
     * Preconditions: None.
     * Postconditions: The character's level is increased by 1, and stats are updated accordingly.
     * Side effects: Updates the character's stats.
     */
    public void levelUp() {
        level++;
        updateStat();
    }

    /**
     * Reduces the character's HP based on the damage taken.
     * Preconditions: None.
     * Postconditions: Character's HP is reduced, and beAttackedCount is updated if applicable.
     * Side effects: Updates beAttackedCount.
     */
    public void takeDamage(double dmg) {
        if(protectlife){
            dmg = 0;
            currentHP -= dmg;
        }else{
            currentHP -= (int) dmg;
            if (currentHP < 0) {
                currentHP = 0;
            }

        }
        beAttackedCount++;

    }
    /**
     * Prints the character's name.
     * Preconditions: None.
     * Postconditions: None.
     * Side effects: print name (but yung mai dai write).
     */
    public void showName() {

    }

    /**
     * Prints the character's attack and defense stats.
     * Preconditions: None.
     * Postconditions: None.
     * Side effects: print atk and def
     */
    public void showStatus(){
        System.out.println("Attack : " + atk);
        System.out.println("Defend : " + def);
    };

    /**
     * Prints the character's HP, Mana, and RunSpeed.
     * Preconditions: None.
     * Postconditions: None.
     * Side effects: print HMR.
     */
    public void ShowHMR(){
        System.out.println("HP Gauge = " + maxHP);
        System.out.println("Mana Gauge = " + maxMana);
        System.out.println("RunSpeed = " + maxRunSpeed);
        System.out.println();
    };
    //same as show name 
    public void showCurrentHP(){
        System.out.println("Current HP: " + currentHP + "/" + maxHP);
    }
    //same as show hp 
    public void showCurrentMana(){
        System.out.println("Current Mana: " + currentMana + "/" + maxMana);
    };
    /**
     * Decreases the character's maxrunspeed by n.
     * Preconditions: None.
     * Postconditions: The character's maxRunSpeed is decreased by n.
     * Side effects: Updates maxRunSpeed + print update
     */
    public void updateRunSpeed(int n){
        maxRunSpeed -= n;
        System.out.println("Run speed updated.");
    };

    /**
     * Updates the character's stats.
     * Preconditions: None.
     * Postconditions: Character's stats are updated based on the current level and other attributes.
     * Side effects: None.
     */
    protected void updateStat(){

    };

    /**
     * Calculates and returns the character's max speed based on the formula.
     * Preconditions: None.
     * Postconditions: None.
     * Side effects: None.
     */
    public double calculateMaxSpeed(){
        return Double.parseDouble(new DecimalFormat("##.##").format(baseRunSpeed * (0.1 + 0.02 * level)));
    };

    /**
     * Returns the character's level.
     * Preconditions: None.
     * Postconditions: None.
     * Side effects: return level
     */
    public int getLevel(){
        return level;
    };

    /**
     * Deals damage to the enemy.
     * Modifies currentEnemyHp and countAtk if applicable.
     * Preconditions: None.
     * Postconditions: Enemy's HP is reduced based on the calculated damage.
     * Side effects: Updates currentEnemyHp and countAtk.
     */
    public void attack(enemy a,int dmg){
        if(CritOn){
            dmg *= Critdmg;
        }
        if(stackAttackedOn){
            dmg *= 1.20;
        }
        a.currentHP -= dmg;
        if(DoubleAttack){
            a.currentHP -= (int)(dmg*0.8);
        }
        currentEnemyHp = a.currentHP;
        countAtk++;
    }

    /**
     * Equips the boots,modifying stat.
     * Preconditions: None.
     * Postconditions: Boots are equipped, and character's stats are modified accordingly.
     * Side effects: Updates equippedBoot, DoubleAttack, and maxRunSpeed.
     */
    public void equipBoot(RPGAccessory.SpeedyBoots x){
        x.equipped = true;
        equippedBoot = true;

        DoubleAttack = x.DoubleAttack((int)maxRunSpeed);
        if(x.boostSpeed(countAtk)){
            maxRunSpeed *= 1.40;
        }

    }
    /**
     * Equips the helmet, modifying equippedHelmet, CritOn, and Critdmg if applicable.
     * Preconditions: None.
     * Postconditions: Helmet is equipped, and character's stats are modified accordingly.
     * Side effects: Updates equippedHelmet, CritOn, and Critdmg.
     */
    public void equiphelmet(RPGAccessory.Helmet x){
        x.equipped = true;
        equippedHelmet = true;

        CritOn = x.RandomCritDamage();
        Critdmg = x.CalculatedCritDamage(CritOn);
    }
    /**
     * Equips the bracelet, modifying equippedbracelet, and stat.
     * Preconditions: None.
     * Postconditions: Bracelet is equipped, and character's stats are modified accordingly.
     * Side effects: Updates equippedbracelet, protectlife, and maxMana.
     */
    public void equipBracelet(RPGAccessory.Bracelets x){
        x.equipped = true;
        equippedbracelet = true;

        protectlife = x.Protectedlife(maxHP,currentHP);
        if(x.ManaBoost(maxMana,currentMana)){
            if(maxMana == currentMana){
                maxMana *= 1.50;
                currentMana = maxMana;
            }else{
                maxMana *= 1.50;
                currentMana *= 1.50;


            }
        }
    }

    /**
     * Equips the gauntlet, modifying equippedGauntlet, atk, and stackAttackedOn if applicable.
     * Preconditions: None.
     * Postconditions: Gauntlet is equipped, and character's stats are modified accordingly.
     * Side effects: Updates equippedGauntlet, atk, and stackAttackedOn.
     */
    public void equipGauntlet(RPGAccessory.Gauntlets x){
        x.equipped = true;
        equippedGauntlet = true;

        atk *= x.BoostAttack(maxHpEnemy,currentEnemyHp);
        if(x.StackAttacked(beAttackedCount)){
            stackAttackedOn = true;
        }
    }



    public static class Mage extends RPGCharacter {
        public Mage(String name) {
            this.name = name;
            this.level = 1;
            baseRunSpeed = 10;
            updateStat();
        }
        @Override
        public void showName() {
            System.out.println("Mage: " + name);
        }


        @Override
        protected void updateStat(){
            maxHP = 100 + 10 * level;
            maxMana = 50 + 5 * level;
            atk = 10 + 2 * level;
            def = 6 + 2 * level;
            maxRunSpeed = calculateMaxSpeed();
            currentHP = maxHP;
            currentMana = maxMana;
        };

        /**
         * spell
         * Preconditions: currentmana >15
         * Postconditions: use attack to enemy
         * Side Effects: -mana
         */
        public void fireball(enemy a){
            if(currentMana < 15){
                System.out.println("require mana at least 15. your mana not enough to use this");
            }else{
                System.out.println("Fireball incant activates");
                System.out.println("take 12 damage");
                attack(a,12);
                currentMana -= 15;
            }
        }
        //same as above just change mana
        public void CallingTides(enemy a){
            if(currentMana < 22){
                System.out.println("require mana at least 22. your mana not enough to use this");
            }else{
                System.out.println("CallingTides incant activates");
                System.out.println("take 25 damage");
                attack(a,25);
                currentMana -= 22;
            }
        }
        /**
         * spell
         * Preconditions: currentmana >=10
         * Postconditions: multiplies stat
         * Side Effects: -mana
         */
        public void PathofTheScholar(){
            if(currentMana < 10){
                System.out.println("require mana at least 10. your mana not enough to use this");
            }else{
                System.out.println("PathofTheScholar incant activates");
                if(maxHP == currentHP){
                    maxHP *= (int) (120.0 /100);
                    currentHP = maxHP;
                }else{
                    maxHP *= (int) (120.0 /100);
                    currentMana *= 1.20;


                }

                if(maxMana == currentMana){
                    maxMana *= (int) (120.0 /100);
                    currentMana = maxMana;
                }else{
                    maxMana *= (int) (120.0 /100);
                }
                atk *= (int) (120.0 /100);
                def *= (int) (120.0 /100);

                currentMana -= 10;
            }
        }
    }


    public static class Warrior extends RPGCharacter {
        private boolean GuardianOn = false;
        public Warrior(String name) {
            this.name = name;
            this.level = 1;
            this.baseRunSpeed = 7;
            updateStat();
        }

        @Override
        public void showName() {
            System.out.println("Warrior: " + name);
        }

        @Override
        public void updateStat() {
            maxHP = 150 + 15 * level;
            maxMana = 40 + 2 * level;
            atk = 6 + 2 * level;
            def = 13 + 3 * level;
            maxRunSpeed = calculateMaxSpeed();
            currentHP = maxHP;
            currentMana = maxMana;
        }
        @Override
        public void takeDamage(double dmg) {

            if(protectlife){
                dmg = 0;
                currentHP -= dmg;
            }else{

                if(!GuardianOn){
                    currentHP -= (int) dmg;
                }else{
                    currentHP -= (int)(dmg * 0.60);
                }
                if (currentHP < 0) {
                    currentHP = 0;
                }
                beAttackedCount++;

            }
        }

        /**
         * spell
         * Preconditions: currentmana >15
         * Postconditions: use attack to enemy
         * Side Effects: -mana
         */
        public void StoneWall(enemy a){
            if(currentMana < 15){
                System.out.println("require mana at least 15. your mana not enough to use this");
            }else{
                System.out.println("StoneWall incant activates");
                System.out.println("take 12 damage");
                attack(a,12);
                currentMana -= 15;
            }
        }
        /**
         * spell
         * Preconditions: currentmana > 8
         * Postconditions: increase def
         * Side Effects: -mana
         */
        public void TheDefender(enemy a){
            if(currentMana < 8){
                System.out.println("require mana at least 8. your mana not enough to use this");
            }else{
                System.out.println("TheDefender incant activates");
                System.out.println("increase def 50%");
                def *= (150/100);
                currentMana -= 8;
            }
        }

        /**
         * spell
         * Preconditions: currentmana > 10
         * Postconditions: set boolean of guaurdian to true
         * Side Effects: -mana
         */
        public void guardian(enemy a){
            if(currentMana < 10){
                System.out.println("require mana at least 10. your mana not enough to use this");
            }else{
                System.out.println("guardian incant activates");
                System.out.println("prevent attack for one hit");
                GuardianOn = true;
                currentMana -= 10;
            }
        }
    }

    public static class enemy extends RPGCharacter{
        private final String name;
        private double atk = 5;
        private int def = 6;
        private int maxHP = 200;
        private int currentHP= maxHP;

        public enemy(String name){
            this.name = name;
            maxHpEnemy = maxHP;
        }

        public void Showhp(){
            System.out.println(name + " : " + currentHP);
        }
    }
}
