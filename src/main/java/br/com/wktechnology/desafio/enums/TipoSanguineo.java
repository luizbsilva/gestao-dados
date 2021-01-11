package br.com.wktechnology.desafio.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoSanguineo implements EnumConverter<Integer> {

    A_POSITIVO(1, "A+"),
    A_NEGATIVO(2, "A-"),
    B_POSITIVO(3, "B+"),
    B_NEGATIVO(4, "B-"),
    AB_POSITIVO(5, "AB+"),
    AB_NEGATIVO(6, "AB-"),
    O_POSITIVO(7, "O+"),
    O_NEGATIVO(8, "O-");

    public final String descricao;

    private Integer codigo;

    private static final Map<String, TipoSanguineo> tipoPorValor = new HashMap<>();

    static {
        for (TipoSanguineo tipoSanguineo: TipoSanguineo.values()){
            tipoPorValor.put(tipoSanguineo.getDescricao(), tipoSanguineo);
        }
    }

    TipoSanguineo(Integer codigo, String descricao) {
        this.descricao = descricao;
        this.codigo = codigo;

    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }

    public final String getDescricao() {
        return this.descricao;
    }

    public static TipoSanguineo getTipoSanguineoPorDescricao(String valor){
        return tipoPorValor.get(valor);
    }
}
