package controller;

import model.PaperRecyclingModel;
import view.interfaces.IPaperRecyclingView;

public class PaperRecyclingController {

    private IPaperRecyclingView view;
    private PaperRecyclingModel model;

    /**
     * Constructor for the class.
     *
     * @param view - represented by the controller
     */
    public PaperRecyclingController(IPaperRecyclingView view) {
        this.view = view;
        this.model = new PaperRecyclingModel();

    }

    /**
     * Retrieves amount of recycled paper and sends this to the view to update.
     */
    public void updateViewWithDate() {

        float paperRecycling = model.getPaperRecyclingCount();
        view.updatePaperRecyclingCounter(paperRecycling);
    }

    /**
     * The method acts upon the fact of the add recycled paper.
     *
     * @param amountString - amount of the produced recycled paper
     */
    public void addPaperRecycling(String amountString) {
        float kg = 0;
        try {
            kg = Float.parseFloat(amountString);
            if (kg < 0) {
                view.displayStatus("Please enter the weight above 0 kg");
                return;
            }
            model.addAmountPaperRecycling(kg);
            view.displayStatus("The stat of recycled paper is updated!");
            updateViewWithDate();
        } catch (NumberFormatException e) {
            view.displayStatus("Please enter a number.");
        }
    }


}
