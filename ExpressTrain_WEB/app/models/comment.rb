class Comment < ActiveRecord::Base
    belongs_to :comment_list
    belongs_to :member
end
