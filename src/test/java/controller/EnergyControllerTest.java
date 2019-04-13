package controller;

import model.UserProfile;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import view.interfaces.IEnergyView;

public class EnergyControllerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IEnergyView view;

    private EnergyController controller;

    @Before
    public void setup() {
        UserProfile.getInstance().clean();
        controller = new EnergyController(view);
    }

    @Test
    public void testUpdate() {
        controller.updateViewWithData();
        Mockito.verify(view).updateSolarArea(Mockito.anyInt());
        Mockito.verify(view).updateTemperature(Mockito.anyDouble());
    }

    @Test
    public void testSolarAreaAddFailureParse() {
        controller.addSolarArea("fff");
        Mockito.verify(view).displayStatusSolar("Please enter an integer number.");
    }

    @Test
    public void testSolarAreaAddFailureRange() {
        controller.addSolarArea("10000");
        Mockito.verify(view).displayStatusSolar("Please enter a  number in the range 0-100 square meters.");
    }

    @Test
    public void testSolarAreaAddFailureRangeBelow() {
        controller.addSolarArea("-1");
        Mockito.verify(view).displayStatusSolar("Please enter a  number in the range 0-100 square meters.");
    }

    @Test
    public void testAddSolarAreaSuccess() {
        controller.addSolarArea("1");
        Mockito.verify(view).displayStatusSolar("The stat of the solar panels is updated!");
    }

    @Test
    public void testTemperatureChangeParseFailure() {
        controller.changeTemperature("fff");
        Mockito.verify(view).displayStatusTemperature("Please enter a number.");
    }

    @Test
    public void testTemperatureChangeRangeFailureBelow() {
        controller.changeTemperature("15");
        Mockito.verify(view).displayStatusTemperature("Please enter the temperature "
                + "in the range 16-24 degrees Celsius.");
    }

    @Test
    public void testTemperatureChangeRangeFailure() {
        controller.changeTemperature("30");
        Mockito.verify(view).displayStatusTemperature("Please enter the temperature "
                + "in the range 16-24 degrees Celsius.");
    }

    @Test
    public void testChangeTemperatureSuccess() {
        controller.changeTemperature("18");
        Mockito.verify(view).displayStatusTemperature("The stat of the temperature is updated!");
    }
}
