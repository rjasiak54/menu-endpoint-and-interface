import pytest
import menu_interface
import test_cases


@pytest.mark.parametrize("test_input, expected", test_cases.test_cases_equality)
def test_interface(test_input, expected):
    mi = menu_interface.menu_interface()
    ret = mi.requestOrder(test_input)
    assert ret == expected


@pytest.mark.parametrize("test_input, expected", test_cases.test_cases_inequality)
def test_interface_bad_messages(test_input, expected):
    mi = menu_interface.menu_interface()
    ret = mi.requestOrder(test_input)
    assert ret != expected