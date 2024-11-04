package br.com.my.hotel.projections;

public interface CheckInSpendingView {
    String getNome();
    String getDocumento();
    String getTelefone();
    Double getValorTotal();
    Double getValorUltimaHospedagem();
}
