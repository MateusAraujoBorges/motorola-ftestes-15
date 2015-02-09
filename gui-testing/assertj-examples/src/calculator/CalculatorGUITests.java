package calculator;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.image.ScreenshotTaker;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

public class CalculatorGUITests extends AssertJSwingJUnitTestCase {

	private FrameFixture window;

	@Override
	protected void onSetUp() {
		Calculator frame = GuiActionRunner
				.execute(new GuiQuery<Calculator>() {
					protected Calculator executeInEDT() {
						return new Calculator();
					}
				});
		// IMPORTANT: note the call to 'robot()'
		// we must use the Robot from AssertJSwingJUnitTestCase
		window = new FrameFixture(robot(), frame);
		window.show(); // shows the frame to test
	}
	
	@Test
	public void testSum() {
		window.button("2").click();
		window.button("+").click();
		window.button("2").click();
		window.button("=").click();
		window.textBox("lcd").requireText("4.0");
	}
	
	@Test
	public void testSumWithSnapshot() {
		window.button("3").click();
		window.button("+").click();
		window.button("3").click();
		window.button("=").click();
		
		try {
			window.textBox("lcd").requireText("6");
		} catch (Exception e) {
			ScreenshotTaker taker = new ScreenshotTaker();
			taker.saveDesktopAsPng("test.png");
			throw e;
		}
	}
}
