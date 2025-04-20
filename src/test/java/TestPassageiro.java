import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPassageiro {

    @Test
    public void criacaoDePassageiro() {
        Passageiro p = new Passageiro("joao", 25);
        assertEquals("joao", p.getNome(), "O ID do passageiro deve ser inicializado corretamente.");
        assertEquals(25, p.getIdade(), "A idade do passageiro deve ser inicializada corretamente.");
    }

    @Test
    public void testToString() {
        Passageiro p = new Passageiro("ana", 67);
        assertEquals("ana:67", p.toString(), "A representação em string do passageiro está incorreta.");
    }

    @Test
    public void prioridadeVerdadeira() {
        Passageiro passageiro = new Passageiro("Guthyerri", 160);
        assertTrue(passageiro.ePrioritario(), "O passageiro é prioridade");
    }

    @Test
    public void falsaPrioridade(){
        Passageiro passageiro = new Passageiro("Guthyerri", 19);
        assertFalse(passageiro.ePrioritario(), "O passageiro é prioridade");
    }


}