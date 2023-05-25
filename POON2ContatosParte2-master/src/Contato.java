import java.io.Serializable;
import java.time.LocalDate;

public class Contato extends Pessoa implements Serializable {

    private String numeroTelefone;

    public Contato(String nome, String numeroTelefone, LocalDate dataNascimento) {
        super(nome, dataNascimento);
        this.numeroTelefone = numeroTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + numeroTelefone;
    }
}