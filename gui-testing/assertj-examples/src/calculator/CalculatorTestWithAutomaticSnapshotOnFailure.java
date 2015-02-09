package calculator;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.junit.v4_5.runner.GUITestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GUITestRunner.class)
public class CalculatorTestWithAutomaticSnapshotOnFailure extends AssertJSwingJUnitTestCase {

	private FrameFixture window;

	@Override
	protected void onSetUp() {
		Calculator frame = GuiActionRunner.execute(new GuiQuery<Calculator>() {
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
	@GUITest
	public void testSum() {
		window.button("4").click();
		window.button("+").click();
		window.button("4").click();
		window.button("=").click();
		window.textBox("lcd").requireText("8");

		// ScreenshotTaker taker = new ScreenshotTaker();
		// taker.saveDesktopAsPng("test.png");
	}

}
