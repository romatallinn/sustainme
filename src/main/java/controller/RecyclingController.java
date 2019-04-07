package controller;

import model.RecyclingModel;
import view.interfaces.IRecyclingView;

public class RecyclingController {

    private IRecyclingView view;
    private RecyclingModel model;

    /**
     * Constructor for the class.
     *
     * @param view - represented by the controller
     */
    public RecyclingController(IRecyclingView view) {
        this.view = view;
        this.model = new RecyclingModel();

    }

    /**
     * Retrieves amount of recycled paper and sends this to the view to update.
     */
    public void updateViewWithDate() {

        float paperRecycling = model.getPaperRecyclingCount();
        view.updatePaperRecyclingCounter(paperRecycling);

        float plasticRecycling = model.getPlasticRecyclingCount();
        view.updatePlasticRecyclingCounter(plasticRecycling);
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

    /**
     * The method acts upon the fact of the add recycled plastic.
     *
     * @param amountString - amount of the produced recycled plastic
     */
    public void addPlasticRecycling(String amountString) {
        float kg = 0;
        try {
            kg = Float.parseFloat(amountString);
            if (kg < 0) {
                view.displayStatus("Please enter the weight above 0 kg");
                return;
            }
            model.addAmountPlasticRecycling(kg);
            view.displayStatus("The stat of recycled plastic is updated!");
            updateViewWithDate();
        } catch (NumberFormatException e) {
            view.displayStatus("Please enter a number.");
        }
    }

}
