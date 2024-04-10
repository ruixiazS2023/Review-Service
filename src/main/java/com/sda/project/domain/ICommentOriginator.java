package com.sda.project.domain;

public interface ICommentOriginator {
    CommentMemento createMemento();
    void restoreFromMemento(CommentMemento memento);
}
