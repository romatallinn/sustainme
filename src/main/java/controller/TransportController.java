package controller;

import view.interfaces.ITransportView;

public class TransportController {

    private ITransportView view;

    /*
    *Constructor for the Class.
    *@param view that is implemented
    */
    public TransportController(ITransportView view) {
        this.view = view;
    }
}
