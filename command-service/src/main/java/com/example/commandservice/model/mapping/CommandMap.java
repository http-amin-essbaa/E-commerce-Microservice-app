package com.example.commandservice.model.mapping;

import com.example.commandservice.model.dto.CommandDto;
import com.example.commandservice.model.dto.ProductItemDto;
import com.example.commandservice.model.entity.Command;
import com.example.commandservice.model.entity.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandMap {
    private final ProductItemMap productItemMap;

    public CommandDto toCommandDto(Command command) {
        // Convertir la liste des ProductItem en ProductItemDto avec protection null
        List<ProductItemDto> productItemDtos = Optional.ofNullable(command.getProductItem())
                .orElse(Collections.emptyList())
                .stream()
                .map(productItemMap::toProductItemDto)
                .collect(Collectors.toList());

        // Construire et retourner le CommandDto
        return CommandDto.builder()
                .id(command.getId())
                .command(command.getCommand())
                .description(command.getDescription())
                .createdAt(command.getCreatedAt())
                .customer_id(command.getCustomerId())
                .customer(command.getCustomerDto())
                .total_price(command.getTotal_price())
                .productItem(productItemDtos)
                .build();
    }

    public Command toCommand(CommandDto commandDto) {
        // Convertir la liste des ProductItemDto en ProductItem avec protection null
        List<ProductItem> productItems = Optional.ofNullable(commandDto.getProductItem())
                .orElse(Collections.emptyList())
                .stream()
                .map(productItemMap::toProductItem)
                .collect(Collectors.toList());

        // Construire et retourner la Command
        return Command.builder()
                .id(commandDto.getId())
                .command(commandDto.getCommand())
                .description(commandDto.getDescription())
                .createdAt(commandDto.getCreatedAt())
                .customerDto(commandDto.getCustomer())
                .customerId(commandDto.getCustomer_id())
                .total_price(commandDto.getTotal_price())
                .productItem(productItems)
                .build();
    }
}