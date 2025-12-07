package helps;

public class Constants {

    public static class Projectiles{
        public static final int ARROW = 0;
        public static final int CHAINS = 1;
        public static final int BOMB = 2;

        public static float GetSpeed(int type){//mermilerin hizi
            switch (type){
                case ARROW:
                    return 8f;
                case BOMB:
                    return 4f;
                case CHAINS:
                    return 6f;
            }
            return 0f;
        }
    }

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class Enemies{

        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;

        public static int GetEnemyReward(int enemyType){//dusmanlar oldugunde ne kadar gold verecegi
            switch (enemyType){
                case ORC:
                    return 10;
                case BAT:
                    return 15;
                case KNIGHT:
                    return 20;
            }
            return 0;
        }

        public static int GetEnemyDamage(int enemyType){
            switch (enemyType){
                case ORC:
                    return 15;
                case BAT:
                    return 15;
                case KNIGHT:
                    return 20;
            }
            return 0;
        }

        public static int GetEnemyArmor(int enemyType){
            switch (enemyType){
                case ORC:
                    return 0;
                case BAT:
                    return 0;
                case KNIGHT:
                    return 50;
            }
            return 0;
        }

        public static float GetSpeed(int enemyType){//dusmanlarin hizi
            switch (enemyType){
                case ORC:
                    return 0.5f;
                case BAT:
                    return 0.75f;
                case KNIGHT:
                    return 0.25f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType) {//dusmanlarin baslangicdaki sagligi
            switch (enemyType){
                case ORC:
                    return 100;
                case BAT:
                    return 100;
                case KNIGHT:
                    return 150;
            }
            return 0;
        }
        public static String GetEnemyName(int enemyType) {
            switch (enemyType) {
                case ORC:
                    return "Goblin Yagmacisi";
                case BAT:
                    return "Golge Gargoyle";
                case KNIGHT:
                    return "Kara Sovalye";
            }
            return "Bilinmeyen Dusman";
        }
    }

    public static class Tiles{
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }

    public static class Towers{
        public static final int CANON = 0;
        public static final int ARCHER = 1;
        public static final int WIZZARD = 2;

        public static int GetTowerCost(int towerType){//kulelerin maaliyeti
            switch (towerType){
                case CANON:
                    return 75;
                case ARCHER:
                    return 50;
                case WIZZARD:
                    return 70;
            }
            return 0;
        }

        public static String GetName(int towerType){//kulelerin isimleri

            switch (towerType){
                case CANON:
                    return "BOMB TOWER";
                case ARCHER:
                    return "ARCHER TOWER";
                case WIZZARD:
                    return "WIZZARD TOWER";
            }
            return "";
        }
        public static int GetStartDamage(int towerType){//kulelerin hasarlari
            switch (towerType){
                case CANON:
                    return 20;
                case ARCHER:
                    return 10;
                case WIZZARD:
                    return 15;
            }
            return 0;
        }

        public static int GetDamage(int damage,int enemyType){
            return (damage*(1-(Enemies.GetEnemyArmor(enemyType)/(Enemies.GetEnemyArmor(enemyType)+100))));
        }
        public static float GetDefaultRange(int towerType){//kulelerin menzili
            switch (towerType){
                case CANON:
                    return 100;
                case ARCHER:
                    return 150;
                case WIZZARD:
                    return 120;
            }
            return 0;
        }
        public static float GetDefaultCooldown(int towerType){//kulelerin ates etmeden once bekledigi/gecen zaman
            switch (towerType){
                case CANON:
                    return 180;
                case ARCHER:
                    return 60;
                case WIZZARD:
                    return 120;
            }
            return 0;
        }

    }
}
