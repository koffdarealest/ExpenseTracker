package koff.expense.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMapper {

    private final ModelMapper modelMapper;

    public <D, T> D convert(T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public <O, T> O convert(T entity, O outEntity) {
        modelMapper.map(entity, outEntity);
        return outEntity;
    }

    public <D, T> List<D> convertList(List<T> entity, Class<D> outClass) {
        return entity.stream().map(e -> convert(e, outClass)).toList();
    }
}
