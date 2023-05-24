package br.com.api.rest.execptions;

import lombok.Data;

@Data
public class MensagemException {

    private String mensagem;

    public MensagemException(String mensagem) {
        this.mensagem = mensagem;
    }
}
