package br.com.senior.hotel.projections;

public interface CheckInSpendingView {
    String getNome();
    String getDocumento();
    String getTelefone();
    Double getValorTotal();
    Double getValorUltimaHospedagem();
}
