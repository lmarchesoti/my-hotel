package br.com.senior.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInSpendingDto {
    public String nome;
    public String documento;
    public String telefone;
    public Double valorTotal;
    public Double valorUltimaHospedagem;
}
