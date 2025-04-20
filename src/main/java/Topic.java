import java.util.Collections;
import java.util.ArrayList;
import java.util.Objects;

public class Topic {
    private final ArrayList<Passageiro> preferenciais;
    private final ArrayList<Passageiro> normais;
    private final int capacidade;
    private final int qtdPrioritatios;

    public Topic(int capacidade, int qtdPrioritatios) {
        this.capacidade = capacidade;
        this.qtdPrioritatios = qtdPrioritatios;
        preferenciais = new ArrayList<>(Collections.nCopies(qtdPrioritatios, null));
        normais = new ArrayList<>(Collections.nCopies(capacidade - qtdPrioritatios, null));
    }

    public int getNumeroAssentosPrioritarios() {
        return qtdPrioritatios;
    }

    public int getNumeroAssentosNormais() {
        return capacidade - qtdPrioritatios;
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        return normais.get(lugar);
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        return preferenciais.get(lugar);
    }

    public int getVagas() {
        return (int) normais.stream().filter(Objects::isNull).count()
                + (int) preferenciais.stream().filter(Objects::isNull).count();
    }

    public boolean subir(Passageiro passageiro) {
        if (isTopicCheia() || isPassageiroPresente(passageiro.getNome())) {
            return false;
        }

        if (!passageiro.ePrioritario()) {
            return auxSubir(passageiro, normais, preferenciais);
        } else {
            return auxSubir(passageiro, preferenciais, normais);
        }

    }

    private boolean auxSubir(Passageiro passageiro, ArrayList<Passageiro> normais, ArrayList<Passageiro> preferenciais) {
        for (int i = 0; i < normais.size(); i++) {
            if (normais.get(i) == null) {
                normais.set(i, passageiro);
                return true;
            }
        }
        for (int i = 0; i < preferenciais.size(); i++) {
            if (preferenciais.get(i) == null) {
                preferenciais.set(i, passageiro);
                return true;
            }
        }
        return false;
    }

    public boolean descer(String nome) {
        return descerPass(nome, normais) || descerPass(nome, preferenciais);
    }

    private boolean descerPass(String nome, ArrayList<Passageiro> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && list.get(i).getNome().equals(nome)) {
                list.set(i, null);
                return true;
            }
        }

        return false;
    }

    boolean isTopicCheia() {
        return getVagas() == 0;
    }

    boolean isPassageiroPresente(String id) {
        return normais.stream().anyMatch(p -> p != null && p.getNome().equals(id))
                || preferenciais.stream().anyMatch(p -> p != null && p.getNome().equals(id));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        // Prioritários
        for (Passageiro passageiro : preferenciais) {
            sb.append("@").append(passageiro != null ? passageiro : "").append(" ");
        }

        // Assentos Normais
        for (Passageiro passageiro : normais) {
            sb.append("=").append(passageiro != null ? passageiro : "").append(" ");
        }
        sb.append("]");

        return sb.toString();
    }
}