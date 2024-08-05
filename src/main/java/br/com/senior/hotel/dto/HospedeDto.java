package br.com.senior.hotel.dto;

import br.com.senior.hotel.entities.Hospede;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDto {
    private String nome;
    private String documento;
    private String telefone;

    public HospedeDto(Hospede hospede) {
        this.nome = hospede.getNome();
        this.documento = hospede.getDocumento();
        this.telefone = hospede.getTelefone();
    }
}
