package br.unitins.top1.model.converterjpa;

import br.unitins.top1.model.TipoPainel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPainelConverter implements AttributeConverter<TipoPainel, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoPainel tipoPainel) {
        if (tipoPainel == null) return null;
        return tipoPainel.getId();
    }

    @Override
    public TipoPainel convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        return TipoPainel.fromId(id);
    }
}