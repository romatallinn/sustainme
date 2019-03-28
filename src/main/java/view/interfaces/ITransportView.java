package view.interfaces;

import controller.TransportController;

public interface ITransportView {

    void initView(TransportController controller);

    void displayStatus(String msg);
}
