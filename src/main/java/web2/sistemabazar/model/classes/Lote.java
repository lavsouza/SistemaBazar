package web2.sistemabazar.model.classes;

import java.time.LocalDate;

public class Lote {
    private int id;
    private String observacao;
    private LocalDate dataEntrega;
    private Integer idOrgaoDonatario;
    private Integer idOrgaoFiscalizador;
    private String orgaoDonatarioNome;
    private String orgaoFiscalizadorNome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrgaoDonatario() {
        return idOrgaoDonatario;
    }

    public void setIdOrgaoDonatario(Integer idOrgaoDonatario) {
        this.idOrgaoDonatario = idOrgaoDonatario;
    }

    public int getIdOrgaoFiscalizador() {
        return idOrgaoFiscalizador;
    }

    public void setIdOrgaoFiscalizador(Integer idOrgaoFiscalizador) {
        this.idOrgaoFiscalizador = idOrgaoFiscalizador;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrada) {
        this.dataEntrega = dataEntrada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getOrgaoDonatarioNome() {
        return orgaoDonatarioNome;
    }

    public void setOrgaoDonatarioNome(String orgaoDonatarioNome) {
        this.orgaoDonatarioNome = orgaoDonatarioNome;
    }

    public String getOrgaoFiscalizadorNome() {
        return orgaoFiscalizadorNome;
    }

    public void setOrgaoFiscalizadorNome(String orgaoFiscalizadorNome) {
        this.orgaoFiscalizadorNome = orgaoFiscalizadorNome;
    }
}
