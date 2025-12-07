package objects;

public class PathPoint {
    private int xCoord;
    private int yCoord;
    public PathPoint(int xCord, int yCord){
        this.xCoord = xCord;
        this.yCoord = yCord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
