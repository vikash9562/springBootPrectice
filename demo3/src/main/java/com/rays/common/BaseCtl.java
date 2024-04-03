package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rays.dto.UserDto;
import com.rays.form.UserForm;
import com.rays.service.UserServiceInt;

public class BaseCtl<F extends BaseForm, T extends BaseDto, S extends BaseServiceInt<T>> {

	private static final String T = null;

	@Autowired
	public S baseservice;

	@Autowired
	public UserServiceInt userservice;

	@PostMapping("add")
	public ORSResponse add(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);
		if (!res.sucess) {
			return res;

		}

		UserDto dto = new UserDto();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		baseservice.add((T) dto);
		res.addMessage("data added successfully");
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);
		if (!res.sucess) {
			return res;

		}
		UserDto dto = new UserDto();
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		baseservice.update((T) dto);
		res.addMessage("data update successfully");
		return res;

	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		T dto = baseservice.findById(id);
		baseservice.delete(id);
		res.addMessage("data deleted successfully");
		return res;

	}

	public ORSResponse validate(BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();
		res.setSucess(true);

		if (bindingResult.hasErrors()) {

			res.setSucess(false);

			Map<String, String> errors = new HashMap<String, String>();
			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());

			});

			res.addInputError(errors);

		}

		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();
		UserDto dto = userservice.findById(id);
		res.addData(dto);
		return res;

	}

	@PostMapping("search")
	public ORSResponse search(@RequestBody F form) {

		ORSResponse res = new ORSResponse();

		T dto = (T) form.getDto();

		List list = baseservice.search(dto, 0, 5);

		if (list.size() == 0) {

			res.addMessage("Result not found");
		} else {
			res.addData(list);
		}
		return res;

	}

}
