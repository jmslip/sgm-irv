package br.com.koinoniatab.controller.admin;

import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.api.dto.UserResponseDTO;
import br.com.koinoniatab.usermanagement.api.service.RoleService;
import br.com.koinoniatab.usermanagement.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {
    private static final String USER_LIST_VIEW = "admin/user-list";
    private static final String USER_FORM_VIEW = "admin/user-form";
    private static final String ALL_ROLES_ATTRIBUTE = "allRoles";

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "name,asc") String[] sort,
                            Model model) {
        String sortField = sort[0];
        String sortDirection = sort.length > 1 ? sort[1] : "asc";
        Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortDirection), sortField);

        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        Page<UserResponseDTO> userPage = userService.findAllUsers(pageable);

        model.addAttribute("userPage", userPage);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return USER_LIST_VIEW;
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("userCreateDTO", UserCreateDTO.builder().roleNames(List.of("ROLE_USER")).build());
        model.addAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAllRoles());
        return USER_FORM_VIEW;
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("userCreateDTO") UserCreateDTO userCreateDTO,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAllRoles());
            return USER_FORM_VIEW;
        }

        try {
            userService.createUser(userCreateDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Usuário criado com sucesso!");
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "error.userCreateDTO", e.getMessage());
            model.addAttribute(ALL_ROLES_ATTRIBUTE, roleService.findAllRoles());
            return USER_FORM_VIEW;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao criar usuário: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }
}
