package org.centrale.objet.WoE;

public class ElementDeJeu {
    private Point2D pos;
    private int type;

    public ElementDeJeu(){}
    public ElementDeJeu(Point2D pos) {
        this.pos = new Point2D(pos);
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

