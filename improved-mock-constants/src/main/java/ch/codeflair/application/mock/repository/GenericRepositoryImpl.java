package ch.codeflair.application.mock.repository;

import ch.codeflair.application.mock.datasource.ImprovedMockConstants;
import org.apache.felix.scr.annotations.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericRepositoryImpl implements GenericRepository<String, Long> {

    @Reference
    ImprovedMockConstants improvedMockConstants = new ImprovedMockConstants();

    @Override
    public Optional<String> findById(Long customerId) {
        return Optional.ofNullable(improvedMockConstants.getCustomersMap().get(customerId));
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(improvedMockConstants.getCustomersMap().values());
    }
}
