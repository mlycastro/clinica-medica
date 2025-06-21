
package clinica.model;

public class Convenio {
    private int id;
    private String nomeEmpresa;
    private String cnpj;
    private String telefone;

    public Convenio() {
    }

    public Convenio(int id, String nomeEmpresa, String cnpj, String telefone) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nomeEmpresa;
    }
}
    