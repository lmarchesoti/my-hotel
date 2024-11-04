package br.com.my.hotel.dto;

import br.com.my.hotel.entities.Hospede;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDto {
    private Long id;
    private String nome;
    private String documento;
    private String telefone;

    public HospedeDto(Hospede hospede) {
        this.id = hospede.getId();
        this.nome = hospede.getNome();
        this.documento = hospede.getDocumento();
        this.telefone = hospede.getTelefone();
    }
}
