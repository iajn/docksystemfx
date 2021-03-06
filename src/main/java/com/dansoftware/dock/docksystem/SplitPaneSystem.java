package com.dansoftware.dock.docksystem;

import com.dansoftware.dock.util.ListObserver;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;

public class SplitPaneSystem extends SplitPane {

    private final ListObserver listObserver =
            new ListObserver(5);

    private SplitPane topHorizontal;
    private SplitPane bottomHorizontal;
    private SplitPane centerHorizontal;
    private SplitPane leftVertical;
    private SplitPane rightVertical;

    public SplitPaneSystem() {
        this.setOrientation(Orientation.VERTICAL);
        this.initSplitPanes();
        this.buildSplitPaneSystem();
    }

    private void buildSplitPaneSystem() {

        listObserver.observeEmpty(topHorizontal.getItems(), empty -> {
            if (empty) this.getItems().remove(topHorizontal);
            else this.getItems().add(0, topHorizontal);
        });

        listObserver.observeEmpty(centerHorizontal.getItems(), empty -> {
            if (empty) this.getItems().remove(centerHorizontal);
            else this.getItems().add(centerHorizontal);
        });

        listObserver.observeEmpty(bottomHorizontal.getItems(), empty -> {
            if (empty) this.getItems().remove(bottomHorizontal);
            else this.getItems().add(this.getItems().size(), bottomHorizontal);
        });

        listObserver.observeEmpty(leftVertical.getItems(), empty -> {
            if (empty) centerHorizontal.getItems().remove(leftVertical);
            else centerHorizontal.getItems().add(0, leftVertical);
        });

        listObserver.observeEmpty(rightVertical.getItems(), empty -> {
            if (empty) centerHorizontal.getItems().remove(rightVertical);
            else centerHorizontal.getItems().add(centerHorizontal.getItems().size(), rightVertical);
        });

        //building the whole splitpane (this)
        //this.getItems().addAll(topHorizontal, centerHorizontal, bottomHorizontal);

        //building the center-horizontal splitpane
        //this.centerHorizontal.getItems().addAll(leftVertical, rightVertical);
    }

    private void initSplitPanes() {
        this.topHorizontal = new SplitPane();
        this.topHorizontal.setOrientation(Orientation.HORIZONTAL);
        SplitPane.setResizableWithParent(topHorizontal, false);

        this.bottomHorizontal = new SplitPane();
        this.bottomHorizontal.setOrientation(Orientation.HORIZONTAL);
        SplitPane.setResizableWithParent(bottomHorizontal, false);

        this.centerHorizontal = new SplitPane();
        this.centerHorizontal.setOrientation(Orientation.HORIZONTAL);
        SplitPane.setResizableWithParent(centerHorizontal, false);

        this.leftVertical = new SplitPane();
        this.leftVertical.setOrientation(Orientation.VERTICAL);
        SplitPane.setResizableWithParent(leftVertical, false);

        this.rightVertical = new SplitPane();
        this.rightVertical.setOrientation(Orientation.VERTICAL);
        SplitPane.setResizableWithParent(rightVertical, false);
    }

    public void setCenterNode(Node centerNode) {
        this.centerHorizontal.getItems().remove(getCenterNode());

        int index = this.centerHorizontal.getItems().isEmpty() ? 0 : 1;
        this.centerHorizontal.getItems().add(index, centerNode);
    }

    public Node getCenterNode() {
        if (this.centerHorizontal.getItems().size() < 3) return null;
        return this.centerHorizontal.getItems().get(1);
    }

    public SplitPane getTopHorizontal() {
        return topHorizontal;
    }

    public SplitPane getBottomHorizontal() {
        return bottomHorizontal;
    }

    public SplitPane getLeftVertical() {
        return leftVertical;
    }

    public SplitPane getRightVertical() {
        return rightVertical;
    }


}
