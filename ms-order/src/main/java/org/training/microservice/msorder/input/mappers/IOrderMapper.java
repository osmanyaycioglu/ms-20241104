package org.training.microservice.msorder.input.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.services.models.Order;

@Mapper
public interface IOrderMapper {
    IOrderMapper ORDER_MAPPER = Mappers.getMapper(IOrderMapper.class);

    Order toOrder(OrderDto orderParam);

    OrderDto toOrder(Order orderParam);


}
