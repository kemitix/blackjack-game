package net.kemitix.blackjack.cli.console;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Tests for {@link BasicConsoleTest}.
 *
 * @author pcampbell
 */
public class BasicConsoleTest {

    private BasicConsole console;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule();

    @Mock
    private BufferedReader in;

    @Mock
    private PrintStream out;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        systemOutRule.enableLog();
        // most tests use the real output to allow the SystemOutRule to
        // harvest the output
        console = new BasicConsole(in, System.out);
    }

    @Test
    public void canGetSystemOutRuleLog() {
        //when
        System.out.print("message");
        //then
        assertThat(systemOutRule.getLog()).isEqualTo("message");
    }

    @Test
    public void shouldPrintln() throws IOException {
        //when
        console.println("text");
        //then
        assertThat(systemOutRule.getLog()).isEqualTo("text\n");
    }

    @Test
    public void shouldPrint() throws IOException {
        //when
        console.print("text");
        //then
        assertThat(systemOutRule.getLog()).isEqualTo("text");
    }

    @Test
    public void shouldReadLine() throws Exception {
        //given
        given(in.readLine()).willReturn("input");
        //then
        assertThat(console.readLine()).isEqualTo("input");
    }

    @Test
    public void readLineShouldThrowConsoleExceptionWhenIOExceptionThrown()
            throws IOException {
        //given
        // use a mock output to allow throwing the IOException
        console = new BasicConsole(in, out);
        doThrow(IOException.class).when(in).readLine();
        exception.expect(ConsoleException.class);
        exception.expectMessage("Error reading from console");
        //when
        console.readLine();
    }
}
