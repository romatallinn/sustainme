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

        double paperRecycling = model.getPaperRecyclingCount();
        view.updatePaperRecyclingCounter(paperRecycling);

        double plasticRecycling = model.getPlasticRecyclingCount();
        view.updatePlasticRecyclingCounter(plasticRecycling);
    }

    /**
     * The method acts upon the fact of the add recycled paper.
     *
     * @param amountString - amount of the produced recycled paper
     */
    public void addPaperRecycling(String amountString) {
        double kg = 0;
        try {
            kg = Double.parseDouble(amountString);
            if (kg < 0) {
                view.displayStatusPaper("Please enter the weight above 0 kg");
                return;
            }
            model.addAmountPaperRecycling(kg);
            view.displayStatusPaper("The stat of recycled paper is updated!");
            updateViewWithDate();
        } catch (NumberFormatException e) {
            view.displayStatusPaper("Please enter a number.");
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
                view.displayStatusPlastic("Please enter the weight above 0 kg");
                return;
            }
            model.addAmountPlasticRecycling(kg);
            view.displayStatusPlastic("The stat of recycled plastic is updated!");
            updateViewWithDate();
        } catch (NumberFormatException e) {
            view.displayStatusPlastic("Please enter a number.");
        }
    }

}
