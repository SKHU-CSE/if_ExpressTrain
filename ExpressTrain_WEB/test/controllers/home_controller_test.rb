require 'test_helper'

class HomeControllerTest < ActionController::TestCase
  test "should get map" do
    get :map
    assert_response :success
  end

  test "should get list" do
    get :list
    assert_response :success
  end

  test "should get notice" do
    get :notice
    assert_response :success
  end

end
