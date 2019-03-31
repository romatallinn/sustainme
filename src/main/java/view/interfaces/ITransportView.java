package view.interfaces;

import controller.TransportController;

public interface ITransportView {

    void initView(TransportController controller);

    void displayStatus(String msg);

    void updateBikeDistance(int distance);

    void updatePublicDistance(int distance);
}
