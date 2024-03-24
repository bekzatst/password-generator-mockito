import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Test
    public void caseOne(){
        Alphabet mockAlphabet = mock(Alphabet.class);
        when(mockAlphabet.getAlphabet()).thenReturn("ABC123");
        Generator generator = new Generator(true, false, true, false);
        generator.alphabet=mockAlphabet;
        assertEquals("ABC123", generator.alphabet.getAlphabet());

        when(mockAlphabet.getAlphabet()).thenReturn("qwerty321!@");
        generator.alphabet=mockAlphabet;
        assertEquals("qwerty321!@", generator.alphabet.getAlphabet());

        when(mockAlphabet.getAlphabet()).thenReturn("123456");
        generator.alphabet=mockAlphabet;
        assertEquals("123456", generator.alphabet.getAlphabet());
    }

    @Test
    public void caseTwo(){
        Password mockPassword = mock(Password.class);
        when(mockPassword.toString()).thenReturn("Test123!");
        when(mockPassword.PasswordStrength()).thenReturn(4);
        when(mockPassword.calculateScore()).thenReturn("Good password");

        int score = mockPassword.PasswordStrength();
        String scoreString = mockPassword.calculateScore();
        assertEquals("Test123!", mockPassword.toString());
        assertEquals(4, score);
        assertEquals("Good password", scoreString);
    }


    @Test
    public void caseThree(){
        Generator mockGenerator = mock(Generator.class);
        Password password1 = new Password("ABCD1234");
        Password password2 = new Password("qwerty1234");
        Password password3 = new Password("123456abcdef");
        when(mockGenerator.GeneratePassword(8)).thenReturn(password1);
        when(mockGenerator.GeneratePassword(10)).thenReturn(password2);
        when(mockGenerator.GeneratePassword(12)).thenReturn(password3);
        Password pass1 = mockGenerator.GeneratePassword(8);
        Password pass2 = mockGenerator.GeneratePassword(10);
        Password pass3 = mockGenerator.GeneratePassword(12);
        assertEquals("ABCD1234", pass1.toString());
        assertEquals("qwerty1234", pass2.toString());
        assertEquals("123456abcdef", pass3.toString());
    }


    @Test
    public void caseFour(){
        Alphabet mockAlphabet = mock(Alphabet.class);
        when(mockAlphabet.getAlphabet()).thenReturn("A");
        Generator generator = new Generator(true, true, true, true);
        generator.alphabet = mockAlphabet;
        Password password = generator.GeneratePassword(8);
        assertEquals("AAAAAAAA", password.toString());
        when(mockAlphabet.getAlphabet()).thenReturn("1");
        Password password1 = generator.GeneratePassword(16);
        assertEquals(3, password1.PasswordStrength());
        when(mockAlphabet.getAlphabet()).thenReturn("b");
        Password password2 = generator.GeneratePassword(5);
        assertEquals("bbbbb", password2.toString());
    }


    @Test
    public void caseFive(){
        Password mockPassword = mock(Password.class);
        Generator generator = new Generator(true, true, true, true);
        when(mockPassword.toString()).thenReturn("123456Abc");
        when(mockPassword.PasswordStrength()).thenReturn(6);
        when(mockPassword.calculateScore()).thenReturn("This is a very good password :D");
        Password pass = mockPassword;
        assertEquals(6, pass.PasswordStrength());
        assertEquals(9, pass.toString().length());
    }
}
