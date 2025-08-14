package web2.sistemabazar.model.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lote {
    private int id;
    private String observacao;
    private LocalDate dataEntrega;
    private Integer idOrgaoDonatario;
    private Integer idOrgaoFiscalizador;
    private String orgaoDonatarioNome;
    private String orgaoFiscalizadorNome;
    private List<Produto> produtos = new ArrayList<Produto>();

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrgaoFiscalizadorNome() {
        return orgaoFiscalizadorNome;
    }

    public void setOrgaoFiscalizadorNome(String orgaoFiscalizadorNome) {
        this.orgaoFiscalizadorNome = orgaoFiscalizadorNome;
    }

    public Integer getIdOrgaoFiscalizador() {
        return idOrgaoFiscalizador;
    }

    public void setIdOrgaoFiscalizador(Integer idOrgaoFiscalizador) {
        this.idOrgaoFiscalizador = idOrgaoFiscalizador;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getIdOrgaoDonatario() {
        return idOrgaoDonatario;
    }

    public void setIdOrgaoDonatario(Integer idOrgaoDonatario) {
        this.idOrgaoDonatario = idOrgaoDonatario;
    }

    public String getOrgaoDonatarioNome() {
        return orgaoDonatarioNome;
    }

    public void setOrgaoDonatarioNome(String orgaoDonatarioNome) {
        this.orgaoDonatarioNome = orgaoDonatarioNome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
