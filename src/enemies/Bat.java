package enemies;

import managers.EnemyManager;

import static helps.Constants.Enemies.BAT;

public class Bat extends Enemy{
    public Bat(float x, float y, int ID, EnemyManager em) {
        super(x, y, ID, BAT,em);
    }
}
