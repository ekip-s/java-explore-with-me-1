package ru.practicum.explorewithme.user.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exception.ConflictException;
import ru.practicum.explorewithme.exception.EntityNotFoundException;
import ru.practicum.explorewithme.user.model.User;
import ru.practicum.explorewithme.user.repository.UserRepository;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    @Override
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException exc) {
            throw new ConflictException("Возник конфликт при сохранении пользователя",
                    String.format("Пользователь с email %s уже существует", user.getEmail()));
        }
    }

    @Override
    public void delete(long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException("Пользователь не найден",
                    String.format("Пользователя с ID %d не существует", userId));
        }
    }

    @Override
    public List<User> getByIds(List<Long> userIds, int from, int size) {
        PageRequest page = getOffset(from, size);
        if (Objects.isNull(userIds)) {
            return userRepository.findAll(page).toList();
        }
        return userRepository.findAllById(userIds);
    }

    private PageRequest getOffset(int from, int size) {
        return PageRequest.of(from, size);
    }
}