package br.unitins.top1.model.converterjpa;

import br.unitins.top1.model.PanelType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoPainelConverter implements AttributeConverter<PanelType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PanelType tipoPainel) {
        if (tipoPainel == null) return null;
        return tipoPainel.getId();
    }

    @Override
    public PanelType convertToEntityAttribute(Integer id) {
        if (id == null) return null;
        return PanelType.fromId(id);
    }
}