package net.nickg.charter.rewards.service;

import net.nickg.charter.rewards.domain.PurchaseRecord;
import net.nickg.charter.rewards.repo.PurchaseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseRecordService {

    private PurchaseRecordRepository repository;


    public PurchaseRecordService(PurchaseRecordRepository repository) {
        this.repository = repository;
    }

    public Iterable<PurchaseRecord> save(List<PurchaseRecord> records) {
        return repository.saveAll(records);
    }

    public List<PurchaseRecord> getAll() {
        return repository.findAll().stream().toList();
    }

    public List<PurchaseRecord> getAllForCustomer(Long customerId) {
        return repository.findAllByCustomerId(customerId).stream().toList();
    }

    public List<Long> getAllCustomerIds() {
        return repository.findAll()
                .stream()
                .map(PurchaseRecord::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
    }
}
