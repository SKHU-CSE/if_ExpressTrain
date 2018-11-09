require 'test_helper'

class PostControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
  end

  test "should get wirte" do
    get :wirte
    assert_response :success
  end

end
